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
import com.example.service.events.repository.PublicDispatchTypeRepository;
import com.example.service.events.model.system.SystemPublicDispatchTypeModel;
import com.example.service.events.request.system.SystemPublicDispatchTypeRequest;
import com.example.service.events.response.system.SystemPublicDispatchTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicDispatchTypeService {

    @Qualifier("serviceEvents.publicDispatchTypeRepository")
    private final PublicDispatchTypeRepository publicDispatchTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicDispatchTypeResponse.Create create(
        SystemPublicDispatchTypeRequest.Context context,
        SystemPublicDispatchTypeRequest.Create request
    ) {

        PublicDispatchTypeEntity.PublicDispatchTypeEntityBuilder builder = PublicDispatchTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicDispatchTypeEntity entity = publicDispatchTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchTypeModel created = conversionService.convert(entity, SystemPublicDispatchTypeModel.class);

        return SystemPublicDispatchTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicDispatchTypeResponse.Remove remove(
        SystemPublicDispatchTypeRequest.Context context,
        SystemPublicDispatchTypeRequest.Id key
    ) {
        PublicDispatchTypeEntity entity = publicDispatchTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchTypeModel removed = conversionService.convert(entity, SystemPublicDispatchTypeModel.class);

        publicDispatchTypeRepository.delete(entity);

        return SystemPublicDispatchTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicDispatchTypeResponse.RemoveAll removeAll(
        SystemPublicDispatchTypeRequest.Context context,
        Collection<SystemPublicDispatchTypeRequest.Id> keys
    ) {
        Collection<PublicDispatchTypeEntity> entities = publicDispatchTypeRepository
                .findAllById(keys.stream().map(SystemPublicDispatchTypeRequest.Id::getId).toList());

        Collection<SystemPublicDispatchTypeModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicDispatchTypeModel.class))
                .toList();

        publicDispatchTypeRepository.deleteAll(entities);

        return SystemPublicDispatchTypeResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicDispatchTypeResponse.Update update(
        SystemPublicDispatchTypeRequest.Context context,
        SystemPublicDispatchTypeRequest.Update request
    ) {

        Specification<PublicDispatchTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicDispatchTypeEntity entity = publicDispatchTypeRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicDispatchTypeEntity.PublicDispatchTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicDispatchTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchTypeModel updated = conversionService.convert(entity, SystemPublicDispatchTypeModel.class);

        return SystemPublicDispatchTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicDispatchTypeResponse.Replace replace(
        SystemPublicDispatchTypeRequest.Context context,
        SystemPublicDispatchTypeRequest.Id key,
        SystemPublicDispatchTypeRequest.Replace request
    ) {
        PublicDispatchTypeEntity entity = publicDispatchTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchTypeModel removed = conversionService.convert(entity, SystemPublicDispatchTypeModel.class);

        PublicDispatchTypeEntity.PublicDispatchTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicDispatchTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchTypeModel created = conversionService.convert(entity, SystemPublicDispatchTypeModel.class);

        return SystemPublicDispatchTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicDispatchTypeModel> select(
        SystemPublicDispatchTypeRequest.Context context,
        SystemPublicDispatchTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicDispatchTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicDispatchTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicDispatchTypeModel.class));
    }
    
    @Transactional
    public SystemPublicDispatchTypeModel get(
        SystemPublicDispatchTypeRequest.Context context,
        SystemPublicDispatchTypeRequest.Id key
    ) {
        PublicDispatchTypeEntity entity = publicDispatchTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchTypeModel result = conversionService.convert(entity, SystemPublicDispatchTypeModel.class);
        return result;
    }
}
