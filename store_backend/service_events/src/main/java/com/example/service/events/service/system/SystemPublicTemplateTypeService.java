package com.example.service.events.service.system;

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

import com.example.service.events.entity.*;
import com.example.service.events.repository.PublicTemplateTypeRepository;
import com.example.service.events.model.system.SystemPublicTemplateTypeModel;
import com.example.service.events.request.system.SystemPublicTemplateTypeRequest;
import com.example.service.events.response.system.SystemPublicTemplateTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicTemplateTypeService {

    @Qualifier("serviceEvents.publicTemplateTypeRepository")
    private final PublicTemplateTypeRepository publicTemplateTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicTemplateTypeResponse.Create create(
        SystemPublicTemplateTypeRequest.Context context,
        SystemPublicTemplateTypeRequest.Create request
    ) {

        PublicTemplateTypeEntity.PublicTemplateTypeEntityBuilder builder = PublicTemplateTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicTemplateTypeEntity entity = publicTemplateTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateTypeModel created = conversionService.convert(entity, SystemPublicTemplateTypeModel.class);

        return SystemPublicTemplateTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateTypeResponse.Remove remove(
        SystemPublicTemplateTypeRequest.Context context,
        SystemPublicTemplateTypeRequest.Id key
    ) {
        PublicTemplateTypeEntity entity = publicTemplateTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateTypeModel removed = conversionService.convert(entity, SystemPublicTemplateTypeModel.class);

        publicTemplateTypeRepository.delete(entity);

        return SystemPublicTemplateTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateTypeResponse.RemoveAll removeAll(
        SystemPublicTemplateTypeRequest.Context context,
        Collection<SystemPublicTemplateTypeRequest.Id> keys
    ) {
        Collection<PublicTemplateTypeEntity> entities = publicTemplateTypeRepository
                .findAllById(keys.stream().map(SystemPublicTemplateTypeRequest.Id::getId).toList());

        Collection<SystemPublicTemplateTypeModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicTemplateTypeModel.class))
                .toList();

        publicTemplateTypeRepository.deleteAll(entities);

        return SystemPublicTemplateTypeResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateTypeResponse.Update update(
        SystemPublicTemplateTypeRequest.Context context,
        SystemPublicTemplateTypeRequest.Update request
    ) {

        Specification<PublicTemplateTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicTemplateTypeEntity entity = publicTemplateTypeRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicTemplateTypeEntity.PublicTemplateTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicTemplateTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateTypeModel updated = conversionService.convert(entity, SystemPublicTemplateTypeModel.class);

        return SystemPublicTemplateTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateTypeResponse.Replace replace(
        SystemPublicTemplateTypeRequest.Context context,
        SystemPublicTemplateTypeRequest.Id key,
        SystemPublicTemplateTypeRequest.Replace request
    ) {
        PublicTemplateTypeEntity entity = publicTemplateTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateTypeModel removed = conversionService.convert(entity, SystemPublicTemplateTypeModel.class);

        PublicTemplateTypeEntity.PublicTemplateTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicTemplateTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateTypeModel created = conversionService.convert(entity, SystemPublicTemplateTypeModel.class);

        return SystemPublicTemplateTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicTemplateTypeModel> select(
        SystemPublicTemplateTypeRequest.Context context,
        SystemPublicTemplateTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicTemplateTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicTemplateTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicTemplateTypeModel.class));
    }
    
    @Transactional
    public SystemPublicTemplateTypeModel get(
        SystemPublicTemplateTypeRequest.Context context,
        SystemPublicTemplateTypeRequest.Id key
    ) {
        PublicTemplateTypeEntity entity = publicTemplateTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateTypeModel result = conversionService.convert(entity, SystemPublicTemplateTypeModel.class);
        return result;
    }
}
