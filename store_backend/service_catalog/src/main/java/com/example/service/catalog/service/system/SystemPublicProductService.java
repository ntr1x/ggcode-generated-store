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
import com.example.service.catalog.repository.PublicProductRepository;
import com.example.service.catalog.model.system.SystemPublicProductModel;
import com.example.service.catalog.request.system.SystemPublicProductRequest;
import com.example.service.catalog.response.system.SystemPublicProductResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicProductService {

    @Qualifier("serviceCatalog.publicProductRepository")
    private final PublicProductRepository publicProductRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "public_product", type = "created", source = "service:service_catalog", payloadEl = "#result.created")
    public SystemPublicProductResponse.Create create(
        SystemPublicProductRequest.Context context,
        SystemPublicProductRequest.Create request
    ) {

        PublicProductEntity.PublicProductEntityBuilder builder = PublicProductEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getPrice())
                .ifPresent((value) -> builder.price(value.orElse(null)));
        Optional.ofNullable(request.getEan13())
                .ifPresent((value) -> builder.ean13(value.orElse(null)));
        Optional.ofNullable(request.getCategoryId())
                .ifPresent((value) -> builder.categoryId(value.orElse(null)));

        PublicProductEntity entity = publicProductRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicProductModel created = conversionService.convert(entity, SystemPublicProductModel.class);

        return SystemPublicProductResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_product", type = "removed", source = "service:service_catalog", payloadEl = "#result.removed")
    public SystemPublicProductResponse.Remove remove(
        SystemPublicProductRequest.Context context,
        SystemPublicProductRequest.Id key
    ) {
        PublicProductEntity entity = publicProductRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicProductModel removed = conversionService.convert(entity, SystemPublicProductModel.class);

        publicProductRepository.delete(entity);

        return SystemPublicProductResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_product", type = "updated", source = "service:service_catalog", payloadEl = "#result.updated")
    public SystemPublicProductResponse.Update update(
        SystemPublicProductRequest.Context context,
        SystemPublicProductRequest.Update request
    ) {

        Specification<PublicProductEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicProductEntity entity = publicProductRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicProductEntity.PublicProductEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getPrice())
                .ifPresent((value) -> builder.price(value.orElse(null)));
        Optional.ofNullable(request.getEan13())
                .ifPresent((value) -> builder.ean13(value.orElse(null)));
        Optional.ofNullable(request.getCategoryId())
                .ifPresent((value) -> builder.categoryId(value.orElse(null)));

        entity = publicProductRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicProductModel updated = conversionService.convert(entity, SystemPublicProductModel.class);

        return SystemPublicProductResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_product", type = "removed", source = "service:service_catalog", payloadEl = "#result.removed")
    @Event(topic = "public_product", type = "created", source = "service:service_catalog", payloadEl = "#result.created")
    public SystemPublicProductResponse.Replace replace(
        SystemPublicProductRequest.Context context,
        SystemPublicProductRequest.Id key,
        SystemPublicProductRequest.Replace request
    ) {
        PublicProductEntity entity = publicProductRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicProductModel removed = conversionService.convert(entity, SystemPublicProductModel.class);

        PublicProductEntity.PublicProductEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getPrice())
                .ifPresent((value) -> builder.price(value.orElse(null)));
        Optional.ofNullable(request.getEan13())
                .ifPresent((value) -> builder.ean13(value.orElse(null)));
        Optional.ofNullable(request.getCategoryId())
                .ifPresent((value) -> builder.categoryId(value.orElse(null)));

        entity = publicProductRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicProductModel created = conversionService.convert(entity, SystemPublicProductModel.class);

        return SystemPublicProductResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicProductModel> select(
        SystemPublicProductRequest.Context context,
        SystemPublicProductRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicProductEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withOptionalMatch("price", request.getPrice())
                    .withOptionalMatch("ean13", request.getEan13())
                    .withOptionalMatch("categoryId", request.getCategoryId())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicProductRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicProductModel.class));
    }
    
    @Transactional
    public SystemPublicProductModel get(
        SystemPublicProductRequest.Context context,
        SystemPublicProductRequest.Id key
    ) {
        PublicProductEntity entity = publicProductRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicProductModel result = conversionService.convert(entity, SystemPublicProductModel.class);
        return result;
    }
}
