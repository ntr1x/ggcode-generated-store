package com.example.service.payments.service.system;

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

import com.example.service.payments.entity.*;
import com.example.service.payments.repository.PublicOrderStatusRepository;
import com.example.service.payments.model.system.SystemPublicOrderStatusModel;
import com.example.service.payments.request.system.SystemPublicOrderStatusRequest;
import com.example.service.payments.response.system.SystemPublicOrderStatusResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicOrderStatusService {

    @Qualifier("servicePayments.publicOrderStatusRepository")
    private final PublicOrderStatusRepository publicOrderStatusRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicOrderStatusResponse.Create create(
        SystemPublicOrderStatusRequest.Context context,
        SystemPublicOrderStatusRequest.Create request
    ) {

        PublicOrderStatusEntity.PublicOrderStatusEntityBuilder builder = PublicOrderStatusEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicOrderStatusEntity entity = publicOrderStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderStatusModel created = conversionService.convert(entity, SystemPublicOrderStatusModel.class);

        return SystemPublicOrderStatusResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderStatusResponse.Remove remove(
        SystemPublicOrderStatusRequest.Context context,
        SystemPublicOrderStatusRequest.Id key
    ) {
        PublicOrderStatusEntity entity = publicOrderStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderStatusModel removed = conversionService.convert(entity, SystemPublicOrderStatusModel.class);

        publicOrderStatusRepository.delete(entity);

        return SystemPublicOrderStatusResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderStatusResponse.Update update(
        SystemPublicOrderStatusRequest.Context context,
        SystemPublicOrderStatusRequest.Update request
    ) {

        Specification<PublicOrderStatusEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicOrderStatusEntity entity = publicOrderStatusRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicOrderStatusEntity.PublicOrderStatusEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicOrderStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderStatusModel updated = conversionService.convert(entity, SystemPublicOrderStatusModel.class);

        return SystemPublicOrderStatusResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderStatusResponse.Replace replace(
        SystemPublicOrderStatusRequest.Context context,
        SystemPublicOrderStatusRequest.Id key,
        SystemPublicOrderStatusRequest.Replace request
    ) {
        PublicOrderStatusEntity entity = publicOrderStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderStatusModel removed = conversionService.convert(entity, SystemPublicOrderStatusModel.class);

        PublicOrderStatusEntity.PublicOrderStatusEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicOrderStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderStatusModel created = conversionService.convert(entity, SystemPublicOrderStatusModel.class);

        return SystemPublicOrderStatusResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicOrderStatusModel> select(
        SystemPublicOrderStatusRequest.Context context,
        SystemPublicOrderStatusRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicOrderStatusEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("name", request.getName()),
                    predicateFactory.fromOptional("description", request.getDescription()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicOrderStatusRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicOrderStatusModel.class));
    }
    
    @Transactional
    public SystemPublicOrderStatusModel get(
        SystemPublicOrderStatusRequest.Context context,
        SystemPublicOrderStatusRequest.Id key
    ) {
        PublicOrderStatusEntity entity = publicOrderStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderStatusModel result = conversionService.convert(entity, SystemPublicOrderStatusModel.class);
        return result;
    }
}
