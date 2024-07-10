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
import com.example.service.events.repository.PublicEventRepository;
import com.example.service.events.model.system.SystemPublicEventModel;
import com.example.service.events.request.system.SystemPublicEventRequest;
import com.example.service.events.response.system.SystemPublicEventResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicEventService {

    @Qualifier("serviceEvents.publicEventRepository")
    private final PublicEventRepository publicEventRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicEventResponse.Create create(
        SystemPublicEventRequest.Context context,
        SystemPublicEventRequest.Create request
    ) {

        PublicEventEntity.PublicEventEntityBuilder builder = PublicEventEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getTopic())
                .ifPresent((value) -> builder.topic(value.orElse(null)));
        Optional.ofNullable(request.getContentType())
                .ifPresent((value) -> builder.contentType(value.orElse(null)));
        Optional.ofNullable(request.getCeType())
                .ifPresent((value) -> builder.ceType(value.orElse(null)));
        Optional.ofNullable(request.getCeSource())
                .ifPresent((value) -> builder.ceSource(value.orElse(null)));
        Optional.ofNullable(request.getCeSpecification())
                .ifPresent((value) -> builder.ceSpecification(value.orElse(null)));
        Optional.ofNullable(request.getCeId())
                .ifPresent((value) -> builder.ceId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        PublicEventEntity entity = publicEventRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventModel created = conversionService.convert(entity, SystemPublicEventModel.class);

        return SystemPublicEventResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicEventResponse.Remove remove(
        SystemPublicEventRequest.Context context,
        SystemPublicEventRequest.Id key
    ) {
        PublicEventEntity entity = publicEventRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicEventModel removed = conversionService.convert(entity, SystemPublicEventModel.class);

        publicEventRepository.delete(entity);

        return SystemPublicEventResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicEventResponse.Update update(
        SystemPublicEventRequest.Context context,
        SystemPublicEventRequest.Update request
    ) {

        Specification<PublicEventEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicEventEntity entity = publicEventRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicEventEntity.PublicEventEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getTopic())
                .ifPresent((value) -> builder.topic(value.orElse(null)));
        Optional.ofNullable(request.getContentType())
                .ifPresent((value) -> builder.contentType(value.orElse(null)));
        Optional.ofNullable(request.getCeType())
                .ifPresent((value) -> builder.ceType(value.orElse(null)));
        Optional.ofNullable(request.getCeSource())
                .ifPresent((value) -> builder.ceSource(value.orElse(null)));
        Optional.ofNullable(request.getCeSpecification())
                .ifPresent((value) -> builder.ceSpecification(value.orElse(null)));
        Optional.ofNullable(request.getCeId())
                .ifPresent((value) -> builder.ceId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicEventRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventModel updated = conversionService.convert(entity, SystemPublicEventModel.class);

        return SystemPublicEventResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicEventResponse.Replace replace(
        SystemPublicEventRequest.Context context,
        SystemPublicEventRequest.Id key,
        SystemPublicEventRequest.Replace request
    ) {
        PublicEventEntity entity = publicEventRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicEventModel removed = conversionService.convert(entity, SystemPublicEventModel.class);

        PublicEventEntity.PublicEventEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getTopic())
                .ifPresent((value) -> builder.topic(value.orElse(null)));
        Optional.ofNullable(request.getContentType())
                .ifPresent((value) -> builder.contentType(value.orElse(null)));
        Optional.ofNullable(request.getCeType())
                .ifPresent((value) -> builder.ceType(value.orElse(null)));
        Optional.ofNullable(request.getCeSource())
                .ifPresent((value) -> builder.ceSource(value.orElse(null)));
        Optional.ofNullable(request.getCeSpecification())
                .ifPresent((value) -> builder.ceSpecification(value.orElse(null)));
        Optional.ofNullable(request.getCeId())
                .ifPresent((value) -> builder.ceId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicEventRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventModel created = conversionService.convert(entity, SystemPublicEventModel.class);

        return SystemPublicEventResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicEventModel> select(
        SystemPublicEventRequest.Context context,
        SystemPublicEventRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicEventEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("topic", request.getTopic())
                    .withOptionalMatch("contentType", request.getContentType())
                    .withOptionalMatch("ceType", request.getCeType())
                    .withOptionalMatch("ceSource", request.getCeSource())
                    .withOptionalMatch("ceSpecification", request.getCeSpecification())
                    .withOptionalMatch("ceId", request.getCeId())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("payload", request.getPayload())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicEventRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicEventModel.class));
    }
    
    @Transactional
    public SystemPublicEventModel get(
        SystemPublicEventRequest.Context context,
        SystemPublicEventRequest.Id key
    ) {
        PublicEventEntity entity = publicEventRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicEventModel result = conversionService.convert(entity, SystemPublicEventModel.class);
        return result;
    }
}
