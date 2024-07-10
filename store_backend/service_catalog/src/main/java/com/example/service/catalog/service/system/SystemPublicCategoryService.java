package com.example.service.catalog.service.system;

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

import com.example.service.catalog.entity.*;
import com.example.service.catalog.repository.PublicCategoryRepository;
import com.example.service.catalog.model.system.SystemPublicCategoryModel;
import com.example.service.catalog.request.system.SystemPublicCategoryRequest;
import com.example.service.catalog.response.system.SystemPublicCategoryResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicCategoryService {

    @Qualifier("serviceCatalog.publicCategoryRepository")
    private final PublicCategoryRepository publicCategoryRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "public_category", type = "created", source = "service:service_catalog", payloadEl = "#result.created")
    public SystemPublicCategoryResponse.Create create(
        SystemPublicCategoryRequest.Context context,
        SystemPublicCategoryRequest.Create request
    ) {

        PublicCategoryEntity.PublicCategoryEntityBuilder builder = PublicCategoryEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicCategoryEntity entity = publicCategoryRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicCategoryModel created = conversionService.convert(entity, SystemPublicCategoryModel.class);

        return SystemPublicCategoryResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_category", type = "removed", source = "service:service_catalog", payloadEl = "#result.removed")
    public SystemPublicCategoryResponse.Remove remove(
        SystemPublicCategoryRequest.Context context,
        SystemPublicCategoryRequest.Id key
    ) {
        PublicCategoryEntity entity = publicCategoryRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicCategoryModel removed = conversionService.convert(entity, SystemPublicCategoryModel.class);

        publicCategoryRepository.delete(entity);

        return SystemPublicCategoryResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_category", type = "updated", source = "service:service_catalog", payloadEl = "#result.updated")
    public SystemPublicCategoryResponse.Update update(
        SystemPublicCategoryRequest.Context context,
        SystemPublicCategoryRequest.Update request
    ) {

        Specification<PublicCategoryEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicCategoryEntity entity = publicCategoryRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicCategoryEntity.PublicCategoryEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicCategoryRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicCategoryModel updated = conversionService.convert(entity, SystemPublicCategoryModel.class);

        return SystemPublicCategoryResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_category", type = "removed", source = "service:service_catalog", payloadEl = "#result.removed")
    @Event(topic = "public_category", type = "created", source = "service:service_catalog", payloadEl = "#result.created")
    public SystemPublicCategoryResponse.Replace replace(
        SystemPublicCategoryRequest.Context context,
        SystemPublicCategoryRequest.Id key,
        SystemPublicCategoryRequest.Replace request
    ) {
        PublicCategoryEntity entity = publicCategoryRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicCategoryModel removed = conversionService.convert(entity, SystemPublicCategoryModel.class);

        PublicCategoryEntity.PublicCategoryEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicCategoryRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicCategoryModel created = conversionService.convert(entity, SystemPublicCategoryModel.class);

        return SystemPublicCategoryResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicCategoryModel> select(
        SystemPublicCategoryRequest.Context context,
        SystemPublicCategoryRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicCategoryEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicCategoryRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicCategoryModel.class));
    }
    
    @Transactional
    public SystemPublicCategoryModel get(
        SystemPublicCategoryRequest.Context context,
        SystemPublicCategoryRequest.Id key
    ) {
        PublicCategoryEntity entity = publicCategoryRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicCategoryModel result = conversionService.convert(entity, SystemPublicCategoryModel.class);
        return result;
    }
}
