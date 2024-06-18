package com.example.service.events.service.system;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.ntr1x.common.api.annotation.Event;
import org.ntr1x.common.api.service.EvaluatorService;
import org.ntr1x.common.api.service.GeneratorService;
import org.ntr1x.common.jpa.criteria.PredicateFactory;
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

        Specification<PublicEventEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

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

        Specification<PublicEventEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("topic", request.getTopic()),
                    predicateFactory.fromOptional("contentType", request.getContentType()),
                    predicateFactory.fromOptional("ceType", request.getCeType()),
                    predicateFactory.fromOptional("ceSource", request.getCeSource()),
                    predicateFactory.fromOptional("ceSpecification", request.getCeSpecification()),
                    predicateFactory.fromOptional("ceId", request.getCeId()),
                    predicateFactory.fromOptional("createdAt", request.getCreatedAt()),
                    predicateFactory.fromOptional("payload", request.getPayload()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

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
