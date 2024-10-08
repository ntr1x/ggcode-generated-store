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
import com.example.service.events.repository.PublicDispatchStatusRepository;
import com.example.service.events.model.system.SystemPublicDispatchStatusModel;
import com.example.service.events.request.system.SystemPublicDispatchStatusRequest;
import com.example.service.events.response.system.SystemPublicDispatchStatusResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicDispatchStatusService {

    @Qualifier("serviceEvents.publicDispatchStatusRepository")
    private final PublicDispatchStatusRepository publicDispatchStatusRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicDispatchStatusResponse.Create create(
        SystemPublicDispatchStatusRequest.Context context,
        SystemPublicDispatchStatusRequest.Create request
    ) {

        PublicDispatchStatusEntity.PublicDispatchStatusEntityBuilder builder = PublicDispatchStatusEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicDispatchStatusEntity entity = publicDispatchStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchStatusModel created = conversionService.convert(entity, SystemPublicDispatchStatusModel.class);

        return SystemPublicDispatchStatusResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicDispatchStatusResponse.Remove remove(
        SystemPublicDispatchStatusRequest.Context context,
        SystemPublicDispatchStatusRequest.Id key
    ) {
        PublicDispatchStatusEntity entity = publicDispatchStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchStatusModel removed = conversionService.convert(entity, SystemPublicDispatchStatusModel.class);

        publicDispatchStatusRepository.delete(entity);

        return SystemPublicDispatchStatusResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicDispatchStatusResponse.RemoveAll removeAll(
        SystemPublicDispatchStatusRequest.Context context,
        Collection<SystemPublicDispatchStatusRequest.Id> keys
    ) {
        Collection<PublicDispatchStatusEntity> entities = publicDispatchStatusRepository
                .findAllById(keys.stream().map(SystemPublicDispatchStatusRequest.Id::getId).toList());

        Collection<SystemPublicDispatchStatusModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicDispatchStatusModel.class))
                .toList();

        publicDispatchStatusRepository.deleteAll(entities);

        return SystemPublicDispatchStatusResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicDispatchStatusResponse.Update update(
        SystemPublicDispatchStatusRequest.Context context,
        SystemPublicDispatchStatusRequest.Update request
    ) {

        Specification<PublicDispatchStatusEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicDispatchStatusEntity entity = publicDispatchStatusRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicDispatchStatusEntity.PublicDispatchStatusEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicDispatchStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchStatusModel updated = conversionService.convert(entity, SystemPublicDispatchStatusModel.class);

        return SystemPublicDispatchStatusResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicDispatchStatusResponse.Replace replace(
        SystemPublicDispatchStatusRequest.Context context,
        SystemPublicDispatchStatusRequest.Id key,
        SystemPublicDispatchStatusRequest.Replace request
    ) {
        PublicDispatchStatusEntity entity = publicDispatchStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchStatusModel removed = conversionService.convert(entity, SystemPublicDispatchStatusModel.class);

        PublicDispatchStatusEntity.PublicDispatchStatusEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicDispatchStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchStatusModel created = conversionService.convert(entity, SystemPublicDispatchStatusModel.class);

        return SystemPublicDispatchStatusResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicDispatchStatusModel> select(
        SystemPublicDispatchStatusRequest.Context context,
        SystemPublicDispatchStatusRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicDispatchStatusEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicDispatchStatusRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicDispatchStatusModel.class));
    }
    
    @Transactional
    public SystemPublicDispatchStatusModel get(
        SystemPublicDispatchStatusRequest.Context context,
        SystemPublicDispatchStatusRequest.Id key
    ) {
        PublicDispatchStatusEntity entity = publicDispatchStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchStatusModel result = conversionService.convert(entity, SystemPublicDispatchStatusModel.class);
        return result;
    }
}
