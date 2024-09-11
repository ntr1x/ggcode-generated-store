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
import com.example.service.events.repository.PublicSubscriptionTypeRepository;
import com.example.service.events.model.system.SystemPublicSubscriptionTypeModel;
import com.example.service.events.request.system.SystemPublicSubscriptionTypeRequest;
import com.example.service.events.response.system.SystemPublicSubscriptionTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicSubscriptionTypeService {

    @Qualifier("serviceEvents.publicSubscriptionTypeRepository")
    private final PublicSubscriptionTypeRepository publicSubscriptionTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicSubscriptionTypeResponse.Create create(
        SystemPublicSubscriptionTypeRequest.Context context,
        SystemPublicSubscriptionTypeRequest.Create request
    ) {

        PublicSubscriptionTypeEntity.PublicSubscriptionTypeEntityBuilder builder = PublicSubscriptionTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicSubscriptionTypeEntity entity = publicSubscriptionTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSubscriptionTypeModel created = conversionService.convert(entity, SystemPublicSubscriptionTypeModel.class);

        return SystemPublicSubscriptionTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicSubscriptionTypeResponse.Remove remove(
        SystemPublicSubscriptionTypeRequest.Context context,
        SystemPublicSubscriptionTypeRequest.Id key
    ) {
        PublicSubscriptionTypeEntity entity = publicSubscriptionTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSubscriptionTypeModel removed = conversionService.convert(entity, SystemPublicSubscriptionTypeModel.class);

        publicSubscriptionTypeRepository.delete(entity);

        return SystemPublicSubscriptionTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicSubscriptionTypeResponse.RemoveAll removeAll(
        SystemPublicSubscriptionTypeRequest.Context context,
        Collection<SystemPublicSubscriptionTypeRequest.Id> keys
    ) {
        Collection<PublicSubscriptionTypeEntity> entities = publicSubscriptionTypeRepository
                .findAllById(keys.stream().map(SystemPublicSubscriptionTypeRequest.Id::getId).toList());

        Collection<SystemPublicSubscriptionTypeModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicSubscriptionTypeModel.class))
                .toList();

        publicSubscriptionTypeRepository.deleteAll(entities);

        return SystemPublicSubscriptionTypeResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicSubscriptionTypeResponse.Update update(
        SystemPublicSubscriptionTypeRequest.Context context,
        SystemPublicSubscriptionTypeRequest.Update request
    ) {

        Specification<PublicSubscriptionTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicSubscriptionTypeEntity entity = publicSubscriptionTypeRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicSubscriptionTypeEntity.PublicSubscriptionTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicSubscriptionTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSubscriptionTypeModel updated = conversionService.convert(entity, SystemPublicSubscriptionTypeModel.class);

        return SystemPublicSubscriptionTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicSubscriptionTypeResponse.Replace replace(
        SystemPublicSubscriptionTypeRequest.Context context,
        SystemPublicSubscriptionTypeRequest.Id key,
        SystemPublicSubscriptionTypeRequest.Replace request
    ) {
        PublicSubscriptionTypeEntity entity = publicSubscriptionTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSubscriptionTypeModel removed = conversionService.convert(entity, SystemPublicSubscriptionTypeModel.class);

        PublicSubscriptionTypeEntity.PublicSubscriptionTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicSubscriptionTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSubscriptionTypeModel created = conversionService.convert(entity, SystemPublicSubscriptionTypeModel.class);

        return SystemPublicSubscriptionTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicSubscriptionTypeModel> select(
        SystemPublicSubscriptionTypeRequest.Context context,
        SystemPublicSubscriptionTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicSubscriptionTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicSubscriptionTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicSubscriptionTypeModel.class));
    }
    
    @Transactional
    public SystemPublicSubscriptionTypeModel get(
        SystemPublicSubscriptionTypeRequest.Context context,
        SystemPublicSubscriptionTypeRequest.Id key
    ) {
        PublicSubscriptionTypeEntity entity = publicSubscriptionTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSubscriptionTypeModel result = conversionService.convert(entity, SystemPublicSubscriptionTypeModel.class);
        return result;
    }
}
