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
import com.example.service.catalog.repository.PublicAttachmentTypeRepository;
import com.example.service.catalog.model.system.SystemPublicAttachmentTypeModel;
import com.example.service.catalog.request.system.SystemPublicAttachmentTypeRequest;
import com.example.service.catalog.response.system.SystemPublicAttachmentTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicAttachmentTypeService {

    @Qualifier("serviceCatalog.publicAttachmentTypeRepository")
    private final PublicAttachmentTypeRepository publicAttachmentTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicAttachmentTypeResponse.Create create(
        SystemPublicAttachmentTypeRequest.Context context,
        SystemPublicAttachmentTypeRequest.Create request
    ) {

        PublicAttachmentTypeEntity.PublicAttachmentTypeEntityBuilder builder = PublicAttachmentTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicAttachmentTypeEntity entity = publicAttachmentTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicAttachmentTypeModel created = conversionService.convert(entity, SystemPublicAttachmentTypeModel.class);

        return SystemPublicAttachmentTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicAttachmentTypeResponse.Remove remove(
        SystemPublicAttachmentTypeRequest.Context context,
        SystemPublicAttachmentTypeRequest.Id key
    ) {
        PublicAttachmentTypeEntity entity = publicAttachmentTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicAttachmentTypeModel removed = conversionService.convert(entity, SystemPublicAttachmentTypeModel.class);

        publicAttachmentTypeRepository.delete(entity);

        return SystemPublicAttachmentTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicAttachmentTypeResponse.Update update(
        SystemPublicAttachmentTypeRequest.Context context,
        SystemPublicAttachmentTypeRequest.Update request
    ) {

        Specification<PublicAttachmentTypeEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicAttachmentTypeEntity entity = publicAttachmentTypeRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicAttachmentTypeEntity.PublicAttachmentTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicAttachmentTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicAttachmentTypeModel updated = conversionService.convert(entity, SystemPublicAttachmentTypeModel.class);

        return SystemPublicAttachmentTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicAttachmentTypeResponse.Replace replace(
        SystemPublicAttachmentTypeRequest.Context context,
        SystemPublicAttachmentTypeRequest.Id key,
        SystemPublicAttachmentTypeRequest.Replace request
    ) {
        PublicAttachmentTypeEntity entity = publicAttachmentTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicAttachmentTypeModel removed = conversionService.convert(entity, SystemPublicAttachmentTypeModel.class);

        PublicAttachmentTypeEntity.PublicAttachmentTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicAttachmentTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicAttachmentTypeModel created = conversionService.convert(entity, SystemPublicAttachmentTypeModel.class);

        return SystemPublicAttachmentTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicAttachmentTypeModel> select(
        SystemPublicAttachmentTypeRequest.Context context,
        SystemPublicAttachmentTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicAttachmentTypeEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("name", request.getName()),
                    predicateFactory.fromOptional("description", request.getDescription()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicAttachmentTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicAttachmentTypeModel.class));
    }
    
    @Transactional
    public SystemPublicAttachmentTypeModel get(
        SystemPublicAttachmentTypeRequest.Context context,
        SystemPublicAttachmentTypeRequest.Id key
    ) {
        PublicAttachmentTypeEntity entity = publicAttachmentTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicAttachmentTypeModel result = conversionService.convert(entity, SystemPublicAttachmentTypeModel.class);
        return result;
    }
}
