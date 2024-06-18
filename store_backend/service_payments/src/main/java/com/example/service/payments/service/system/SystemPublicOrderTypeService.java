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
import com.example.service.payments.repository.PublicOrderTypeRepository;
import com.example.service.payments.model.system.SystemPublicOrderTypeModel;
import com.example.service.payments.request.system.SystemPublicOrderTypeRequest;
import com.example.service.payments.response.system.SystemPublicOrderTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicOrderTypeService {

    @Qualifier("servicePayments.publicOrderTypeRepository")
    private final PublicOrderTypeRepository publicOrderTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicOrderTypeResponse.Create create(
        SystemPublicOrderTypeRequest.Context context,
        SystemPublicOrderTypeRequest.Create request
    ) {

        PublicOrderTypeEntity.PublicOrderTypeEntityBuilder builder = PublicOrderTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicOrderTypeEntity entity = publicOrderTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderTypeModel created = conversionService.convert(entity, SystemPublicOrderTypeModel.class);

        return SystemPublicOrderTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderTypeResponse.Remove remove(
        SystemPublicOrderTypeRequest.Context context,
        SystemPublicOrderTypeRequest.Id key
    ) {
        PublicOrderTypeEntity entity = publicOrderTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderTypeModel removed = conversionService.convert(entity, SystemPublicOrderTypeModel.class);

        publicOrderTypeRepository.delete(entity);

        return SystemPublicOrderTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderTypeResponse.Update update(
        SystemPublicOrderTypeRequest.Context context,
        SystemPublicOrderTypeRequest.Update request
    ) {

        Specification<PublicOrderTypeEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicOrderTypeEntity entity = publicOrderTypeRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicOrderTypeEntity.PublicOrderTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicOrderTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderTypeModel updated = conversionService.convert(entity, SystemPublicOrderTypeModel.class);

        return SystemPublicOrderTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderTypeResponse.Replace replace(
        SystemPublicOrderTypeRequest.Context context,
        SystemPublicOrderTypeRequest.Id key,
        SystemPublicOrderTypeRequest.Replace request
    ) {
        PublicOrderTypeEntity entity = publicOrderTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderTypeModel removed = conversionService.convert(entity, SystemPublicOrderTypeModel.class);

        PublicOrderTypeEntity.PublicOrderTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicOrderTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderTypeModel created = conversionService.convert(entity, SystemPublicOrderTypeModel.class);

        return SystemPublicOrderTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicOrderTypeModel> select(
        SystemPublicOrderTypeRequest.Context context,
        SystemPublicOrderTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicOrderTypeEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("name", request.getName()),
                    predicateFactory.fromOptional("description", request.getDescription()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicOrderTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicOrderTypeModel.class));
    }
    
    @Transactional
    public SystemPublicOrderTypeModel get(
        SystemPublicOrderTypeRequest.Context context,
        SystemPublicOrderTypeRequest.Id key
    ) {
        PublicOrderTypeEntity entity = publicOrderTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderTypeModel result = conversionService.convert(entity, SystemPublicOrderTypeModel.class);
        return result;
    }
}
