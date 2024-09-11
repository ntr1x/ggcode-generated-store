package com.example.service.catalog.service.system;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.ntr1x.common.api.annotation.Event;
import org.ntr1x.common.api.service.EvaluatorService;
import org.ntr1x.common.api.service.GeneratorService;
import org.ntr1x.common.jpa.criteria.SpecificationBuilder;
import org.ntr1x.common.web.util.Validate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.service.catalog.entity.*;
import com.example.service.catalog.repository.PublicSourceTypeRepository;
import com.example.service.catalog.model.system.SystemPublicSourceTypeModel;
import com.example.service.catalog.request.system.SystemPublicSourceTypeRequest;
import com.example.service.catalog.response.system.SystemPublicSourceTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicSourceTypeService {

    @Qualifier("serviceCatalog.publicSourceTypeRepository")
    private final PublicSourceTypeRepository publicSourceTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicSourceTypeResponse.Create create(
        SystemPublicSourceTypeRequest.Context context,
        SystemPublicSourceTypeRequest.Create request
    ) {

        PublicSourceTypeEntity.PublicSourceTypeEntityBuilder builder = PublicSourceTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicSourceTypeEntity entity = publicSourceTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSourceTypeModel created = conversionService.convert(entity, SystemPublicSourceTypeModel.class);

        return SystemPublicSourceTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicSourceTypeResponse.Remove remove(
        SystemPublicSourceTypeRequest.Context context,
        SystemPublicSourceTypeRequest.Id key
    ) {
        PublicSourceTypeEntity entity = publicSourceTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSourceTypeModel removed = conversionService.convert(entity, SystemPublicSourceTypeModel.class);

        publicSourceTypeRepository.delete(entity);

        return SystemPublicSourceTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicSourceTypeResponse.RemoveAll removeAll(
        SystemPublicSourceTypeRequest.Context context,
        Collection<SystemPublicSourceTypeRequest.Id> keys
    ) {
        Collection<PublicSourceTypeEntity> entities = publicSourceTypeRepository
                .findAllById(keys.stream().map(SystemPublicSourceTypeRequest.Id::getId).toList());

        Collection<SystemPublicSourceTypeModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicSourceTypeModel.class))
                .toList();

        publicSourceTypeRepository.deleteAll(entities);

        return SystemPublicSourceTypeResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicSourceTypeResponse.Update update(
        SystemPublicSourceTypeRequest.Context context,
        SystemPublicSourceTypeRequest.Update request
    ) {

        Specification<PublicSourceTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicSourceTypeEntity entity = publicSourceTypeRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicSourceTypeEntity.PublicSourceTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicSourceTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSourceTypeModel updated = conversionService.convert(entity, SystemPublicSourceTypeModel.class);

        return SystemPublicSourceTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicSourceTypeResponse.Replace replace(
        SystemPublicSourceTypeRequest.Context context,
        SystemPublicSourceTypeRequest.Id key,
        SystemPublicSourceTypeRequest.Replace request
    ) {
        PublicSourceTypeEntity entity = publicSourceTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSourceTypeModel removed = conversionService.convert(entity, SystemPublicSourceTypeModel.class);

        PublicSourceTypeEntity.PublicSourceTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicSourceTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSourceTypeModel created = conversionService.convert(entity, SystemPublicSourceTypeModel.class);

        return SystemPublicSourceTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicSourceTypeModel> select(
        SystemPublicSourceTypeRequest.Context context,
        SystemPublicSourceTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicSourceTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicSourceTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicSourceTypeModel.class));
    }
    
    @Transactional
    public SystemPublicSourceTypeModel get(
        SystemPublicSourceTypeRequest.Context context,
        SystemPublicSourceTypeRequest.Id key
    ) {
        PublicSourceTypeEntity entity = publicSourceTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSourceTypeModel result = conversionService.convert(entity, SystemPublicSourceTypeModel.class);
        return result;
    }
}
