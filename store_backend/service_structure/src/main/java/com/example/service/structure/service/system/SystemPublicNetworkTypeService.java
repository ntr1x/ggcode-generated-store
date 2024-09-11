package com.example.service.structure.service.system;

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

import com.example.service.structure.entity.*;
import com.example.service.structure.repository.PublicNetworkTypeRepository;
import com.example.service.structure.model.system.SystemPublicNetworkTypeModel;
import com.example.service.structure.request.system.SystemPublicNetworkTypeRequest;
import com.example.service.structure.response.system.SystemPublicNetworkTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicNetworkTypeService {

    @Qualifier("serviceStructure.publicNetworkTypeRepository")
    private final PublicNetworkTypeRepository publicNetworkTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicNetworkTypeResponse.Create create(
        SystemPublicNetworkTypeRequest.Context context,
        SystemPublicNetworkTypeRequest.Create request
    ) {

        PublicNetworkTypeEntity.PublicNetworkTypeEntityBuilder builder = PublicNetworkTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicNetworkTypeEntity entity = publicNetworkTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicNetworkTypeModel created = conversionService.convert(entity, SystemPublicNetworkTypeModel.class);

        return SystemPublicNetworkTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicNetworkTypeResponse.Remove remove(
        SystemPublicNetworkTypeRequest.Context context,
        SystemPublicNetworkTypeRequest.Id key
    ) {
        PublicNetworkTypeEntity entity = publicNetworkTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicNetworkTypeModel removed = conversionService.convert(entity, SystemPublicNetworkTypeModel.class);

        publicNetworkTypeRepository.delete(entity);

        return SystemPublicNetworkTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicNetworkTypeResponse.RemoveAll removeAll(
        SystemPublicNetworkTypeRequest.Context context,
        Collection<SystemPublicNetworkTypeRequest.Id> keys
    ) {
        Collection<PublicNetworkTypeEntity> entities = publicNetworkTypeRepository
                .findAllById(keys.stream().map(SystemPublicNetworkTypeRequest.Id::getId).toList());

        Collection<SystemPublicNetworkTypeModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicNetworkTypeModel.class))
                .toList();

        publicNetworkTypeRepository.deleteAll(entities);

        return SystemPublicNetworkTypeResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicNetworkTypeResponse.Update update(
        SystemPublicNetworkTypeRequest.Context context,
        SystemPublicNetworkTypeRequest.Update request
    ) {

        Specification<PublicNetworkTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicNetworkTypeEntity entity = publicNetworkTypeRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicNetworkTypeEntity.PublicNetworkTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicNetworkTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicNetworkTypeModel updated = conversionService.convert(entity, SystemPublicNetworkTypeModel.class);

        return SystemPublicNetworkTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicNetworkTypeResponse.Replace replace(
        SystemPublicNetworkTypeRequest.Context context,
        SystemPublicNetworkTypeRequest.Id key,
        SystemPublicNetworkTypeRequest.Replace request
    ) {
        PublicNetworkTypeEntity entity = publicNetworkTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicNetworkTypeModel removed = conversionService.convert(entity, SystemPublicNetworkTypeModel.class);

        PublicNetworkTypeEntity.PublicNetworkTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicNetworkTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicNetworkTypeModel created = conversionService.convert(entity, SystemPublicNetworkTypeModel.class);

        return SystemPublicNetworkTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicNetworkTypeModel> select(
        SystemPublicNetworkTypeRequest.Context context,
        SystemPublicNetworkTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicNetworkTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicNetworkTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicNetworkTypeModel.class));
    }
    
    @Transactional
    public SystemPublicNetworkTypeModel get(
        SystemPublicNetworkTypeRequest.Context context,
        SystemPublicNetworkTypeRequest.Id key
    ) {
        PublicNetworkTypeEntity entity = publicNetworkTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicNetworkTypeModel result = conversionService.convert(entity, SystemPublicNetworkTypeModel.class);
        return result;
    }
}
