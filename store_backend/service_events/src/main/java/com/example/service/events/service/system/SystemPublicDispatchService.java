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
import com.example.service.events.repository.PublicDispatchRepository;
import com.example.service.events.model.system.SystemPublicDispatchModel;
import com.example.service.events.request.system.SystemPublicDispatchRequest;
import com.example.service.events.response.system.SystemPublicDispatchResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicDispatchService {

    @Qualifier("serviceEvents.publicDispatchRepository")
    private final PublicDispatchRepository publicDispatchRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "public_dispatch", type = "created", source = "service:service_events", payloadEl = "#result.created")
    public SystemPublicDispatchResponse.Create create(
        SystemPublicDispatchRequest.Context context,
        SystemPublicDispatchRequest.Create request
    ) {

        PublicDispatchEntity.PublicDispatchEntityBuilder builder = PublicDispatchEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getSubscriptionId())
                .ifPresent((value) -> builder.subscriptionId(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getStatusId())
                .ifPresent((value) -> builder.statusId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        PublicDispatchEntity entity = publicDispatchRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchModel created = conversionService.convert(entity, SystemPublicDispatchModel.class);

        return SystemPublicDispatchResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_dispatch", type = "removed", source = "service:service_events", payloadEl = "#result.removed")
    public SystemPublicDispatchResponse.Remove remove(
        SystemPublicDispatchRequest.Context context,
        SystemPublicDispatchRequest.Id key
    ) {
        PublicDispatchEntity entity = publicDispatchRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchModel removed = conversionService.convert(entity, SystemPublicDispatchModel.class);

        publicDispatchRepository.delete(entity);

        return SystemPublicDispatchResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_dispatch", type = "updated", source = "service:service_events", payloadEl = "#result.updated")
    public SystemPublicDispatchResponse.Update update(
        SystemPublicDispatchRequest.Context context,
        SystemPublicDispatchRequest.Update request
    ) {

        Specification<PublicDispatchEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicDispatchEntity entity = publicDispatchRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicDispatchEntity.PublicDispatchEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getSubscriptionId())
                .ifPresent((value) -> builder.subscriptionId(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getStatusId())
                .ifPresent((value) -> builder.statusId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicDispatchRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchModel updated = conversionService.convert(entity, SystemPublicDispatchModel.class);

        return SystemPublicDispatchResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_dispatch", type = "removed", source = "service:service_events", payloadEl = "#result.removed")
    @Event(topic = "public_dispatch", type = "created", source = "service:service_events", payloadEl = "#result.created")
    public SystemPublicDispatchResponse.Replace replace(
        SystemPublicDispatchRequest.Context context,
        SystemPublicDispatchRequest.Id key,
        SystemPublicDispatchRequest.Replace request
    ) {
        PublicDispatchEntity entity = publicDispatchRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchModel removed = conversionService.convert(entity, SystemPublicDispatchModel.class);

        PublicDispatchEntity.PublicDispatchEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getSubscriptionId())
                .ifPresent((value) -> builder.subscriptionId(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getStatusId())
                .ifPresent((value) -> builder.statusId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicDispatchRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicDispatchModel created = conversionService.convert(entity, SystemPublicDispatchModel.class);

        return SystemPublicDispatchResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicDispatchModel> select(
        SystemPublicDispatchRequest.Context context,
        SystemPublicDispatchRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicDispatchEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("customerId", request.getCustomerId())
                    .withOptionalMatch("sessionId", request.getSessionId())
                    .withOptionalMatch("subscriptionId", request.getSubscriptionId())
                    .withOptionalMatch("typeId", request.getTypeId())
                    .withOptionalMatch("statusId", request.getStatusId())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("updatedAt", request.getUpdatedAt())
                    .withOptionalMatch("payload", request.getPayload())
                    .withOptionalPathMatch("subscription.id", request, java.util.UUID.class, "subscription.id")
                    .withOptionalPathMatch("subscription.customerId", request, java.util.UUID.class, "subscription.customerId")
                    .withOptionalPathMatch("subscription.typeId", request, java.lang.Integer.class, "subscription.typeId")
                    .withOptionalPathMatch("subscription.sessionId", request, java.lang.String.class, "subscription.sessionId")
                    .withOptionalPathMatch("subscription.createdAt", request, java.time.LocalDateTime.class, "subscription.createdAt")
                    .withOptionalPathMatch("subscription.payload", request, com.fasterxml.jackson.databind.JsonNode.class, "subscription.payload")
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicDispatchRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicDispatchModel.class));
    }
    
    @Transactional
    public SystemPublicDispatchModel get(
        SystemPublicDispatchRequest.Context context,
        SystemPublicDispatchRequest.Id key
    ) {
        PublicDispatchEntity entity = publicDispatchRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicDispatchModel result = conversionService.convert(entity, SystemPublicDispatchModel.class);
        return result;
    }
}
