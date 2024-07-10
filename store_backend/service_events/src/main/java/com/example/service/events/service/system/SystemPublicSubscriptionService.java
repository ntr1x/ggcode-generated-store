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
import com.example.service.events.repository.PublicSubscriptionRepository;
import com.example.service.events.model.system.SystemPublicSubscriptionModel;
import com.example.service.events.request.system.SystemPublicSubscriptionRequest;
import com.example.service.events.response.system.SystemPublicSubscriptionResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicSubscriptionService {

    @Qualifier("serviceEvents.publicSubscriptionRepository")
    private final PublicSubscriptionRepository publicSubscriptionRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicSubscriptionResponse.Create create(
        SystemPublicSubscriptionRequest.Context context,
        SystemPublicSubscriptionRequest.Create request
    ) {

        PublicSubscriptionEntity.PublicSubscriptionEntityBuilder builder = PublicSubscriptionEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        PublicSubscriptionEntity entity = publicSubscriptionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSubscriptionModel created = conversionService.convert(entity, SystemPublicSubscriptionModel.class);

        return SystemPublicSubscriptionResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicSubscriptionResponse.Remove remove(
        SystemPublicSubscriptionRequest.Context context,
        SystemPublicSubscriptionRequest.Id key
    ) {
        PublicSubscriptionEntity entity = publicSubscriptionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSubscriptionModel removed = conversionService.convert(entity, SystemPublicSubscriptionModel.class);

        publicSubscriptionRepository.delete(entity);

        return SystemPublicSubscriptionResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicSubscriptionResponse.Update update(
        SystemPublicSubscriptionRequest.Context context,
        SystemPublicSubscriptionRequest.Update request
    ) {

        Specification<PublicSubscriptionEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicSubscriptionEntity entity = publicSubscriptionRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicSubscriptionEntity.PublicSubscriptionEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicSubscriptionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSubscriptionModel updated = conversionService.convert(entity, SystemPublicSubscriptionModel.class);

        return SystemPublicSubscriptionResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicSubscriptionResponse.Replace replace(
        SystemPublicSubscriptionRequest.Context context,
        SystemPublicSubscriptionRequest.Id key,
        SystemPublicSubscriptionRequest.Replace request
    ) {
        PublicSubscriptionEntity entity = publicSubscriptionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSubscriptionModel removed = conversionService.convert(entity, SystemPublicSubscriptionModel.class);

        PublicSubscriptionEntity.PublicSubscriptionEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicSubscriptionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicSubscriptionModel created = conversionService.convert(entity, SystemPublicSubscriptionModel.class);

        return SystemPublicSubscriptionResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicSubscriptionModel> select(
        SystemPublicSubscriptionRequest.Context context,
        SystemPublicSubscriptionRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicSubscriptionEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("customerId", request.getCustomerId())
                    .withOptionalMatch("typeId", request.getTypeId())
                    .withOptionalMatch("sessionId", request.getSessionId())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("payload", request.getPayload())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicSubscriptionRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicSubscriptionModel.class));
    }
    
    @Transactional
    public SystemPublicSubscriptionModel get(
        SystemPublicSubscriptionRequest.Context context,
        SystemPublicSubscriptionRequest.Id key
    ) {
        PublicSubscriptionEntity entity = publicSubscriptionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicSubscriptionModel result = conversionService.convert(entity, SystemPublicSubscriptionModel.class);
        return result;
    }
}
