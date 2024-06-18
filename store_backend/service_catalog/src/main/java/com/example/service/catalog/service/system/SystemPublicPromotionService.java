package com.example.service.catalog.service.system;

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

import com.example.service.catalog.entity.*;
import com.example.service.catalog.repository.PublicPromotionRepository;
import com.example.service.catalog.model.system.SystemPublicPromotionModel;
import com.example.service.catalog.request.system.SystemPublicPromotionRequest;
import com.example.service.catalog.response.system.SystemPublicPromotionResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicPromotionService {

    @Qualifier("serviceCatalog.publicPromotionRepository")
    private final PublicPromotionRepository publicPromotionRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicPromotionResponse.Create create(
        SystemPublicPromotionRequest.Context context,
        SystemPublicPromotionRequest.Create request
    ) {

        PublicPromotionEntity.PublicPromotionEntityBuilder builder = PublicPromotionEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getConfig())
                .ifPresent((value) -> builder.config(value.orElse(null)));

        PublicPromotionEntity entity = publicPromotionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionModel created = conversionService.convert(entity, SystemPublicPromotionModel.class);

        return SystemPublicPromotionResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionResponse.Remove remove(
        SystemPublicPromotionRequest.Context context,
        SystemPublicPromotionRequest.Id key
    ) {
        PublicPromotionEntity entity = publicPromotionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionModel removed = conversionService.convert(entity, SystemPublicPromotionModel.class);

        publicPromotionRepository.delete(entity);

        return SystemPublicPromotionResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionResponse.Update update(
        SystemPublicPromotionRequest.Context context,
        SystemPublicPromotionRequest.Update request
    ) {

        Specification<PublicPromotionEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicPromotionEntity entity = publicPromotionRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicPromotionEntity.PublicPromotionEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getConfig())
                .ifPresent((value) -> builder.config(value.orElse(null)));

        entity = publicPromotionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionModel updated = conversionService.convert(entity, SystemPublicPromotionModel.class);

        return SystemPublicPromotionResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionResponse.Replace replace(
        SystemPublicPromotionRequest.Context context,
        SystemPublicPromotionRequest.Id key,
        SystemPublicPromotionRequest.Replace request
    ) {
        PublicPromotionEntity entity = publicPromotionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionModel removed = conversionService.convert(entity, SystemPublicPromotionModel.class);

        PublicPromotionEntity.PublicPromotionEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getConfig())
                .ifPresent((value) -> builder.config(value.orElse(null)));

        entity = publicPromotionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionModel created = conversionService.convert(entity, SystemPublicPromotionModel.class);

        return SystemPublicPromotionResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicPromotionModel> select(
        SystemPublicPromotionRequest.Context context,
        SystemPublicPromotionRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicPromotionEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("name", request.getName()),
                    predicateFactory.fromOptional("description", request.getDescription()),
                    predicateFactory.fromOptional("typeId", request.getTypeId()),
                    predicateFactory.fromOptional("config", request.getConfig()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicPromotionRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicPromotionModel.class));
    }
    
    @Transactional
    public SystemPublicPromotionModel get(
        SystemPublicPromotionRequest.Context context,
        SystemPublicPromotionRequest.Id key
    ) {
        PublicPromotionEntity entity = publicPromotionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionModel result = conversionService.convert(entity, SystemPublicPromotionModel.class);
        return result;
    }
}
