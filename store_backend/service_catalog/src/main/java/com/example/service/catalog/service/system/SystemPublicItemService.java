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
import com.example.service.catalog.repository.PublicItemRepository;
import com.example.service.catalog.model.system.SystemPublicItemModel;
import com.example.service.catalog.request.system.SystemPublicItemRequest;
import com.example.service.catalog.response.system.SystemPublicItemResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicItemService {

    @Qualifier("serviceCatalog.publicItemRepository")
    private final PublicItemRepository publicItemRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "public_item", type = "created", source = "service:service_catalog", payloadEl = "#result.created")
    public SystemPublicItemResponse.Create create(
        SystemPublicItemRequest.Context context,
        SystemPublicItemRequest.Create request
    ) {

        PublicItemEntity.PublicItemEntityBuilder builder = PublicItemEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getEan13())
                .ifPresent((value) -> builder.ean13(value.orElse(null)));
        Optional.ofNullable(request.getCode128())
                .ifPresent((value) -> builder.code128(value.orElse(null)));

        PublicItemEntity entity = publicItemRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicItemModel created = conversionService.convert(entity, SystemPublicItemModel.class);

        return SystemPublicItemResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_item", type = "removed", source = "service:service_catalog", payloadEl = "#result.removed")
    public SystemPublicItemResponse.Remove remove(
        SystemPublicItemRequest.Context context,
        SystemPublicItemRequest.Id key
    ) {
        PublicItemEntity entity = publicItemRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicItemModel removed = conversionService.convert(entity, SystemPublicItemModel.class);

        publicItemRepository.delete(entity);

        return SystemPublicItemResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_item", type = "updated", source = "service:service_catalog", payloadEl = "#result.updated")
    public SystemPublicItemResponse.Update update(
        SystemPublicItemRequest.Context context,
        SystemPublicItemRequest.Update request
    ) {

        Specification<PublicItemEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicItemEntity entity = publicItemRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicItemEntity.PublicItemEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getEan13())
                .ifPresent((value) -> builder.ean13(value.orElse(null)));
        Optional.ofNullable(request.getCode128())
                .ifPresent((value) -> builder.code128(value.orElse(null)));

        entity = publicItemRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicItemModel updated = conversionService.convert(entity, SystemPublicItemModel.class);

        return SystemPublicItemResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_item", type = "removed", source = "service:service_catalog", payloadEl = "#result.removed")
    @Event(topic = "public_item", type = "created", source = "service:service_catalog", payloadEl = "#result.created")
    public SystemPublicItemResponse.Replace replace(
        SystemPublicItemRequest.Context context,
        SystemPublicItemRequest.Id key,
        SystemPublicItemRequest.Replace request
    ) {
        PublicItemEntity entity = publicItemRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicItemModel removed = conversionService.convert(entity, SystemPublicItemModel.class);

        PublicItemEntity.PublicItemEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getEan13())
                .ifPresent((value) -> builder.ean13(value.orElse(null)));
        Optional.ofNullable(request.getCode128())
                .ifPresent((value) -> builder.code128(value.orElse(null)));

        entity = publicItemRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicItemModel created = conversionService.convert(entity, SystemPublicItemModel.class);

        return SystemPublicItemResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicItemModel> select(
        SystemPublicItemRequest.Context context,
        SystemPublicItemRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicItemEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("productId", request.getProductId()),
                    predicateFactory.fromOptional("shopId", request.getShopId()),
                    predicateFactory.fromOptional("ean13", request.getEan13()),
                    predicateFactory.fromOptional("code128", request.getCode128()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicItemRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicItemModel.class));
    }
    
    @Transactional
    public SystemPublicItemModel get(
        SystemPublicItemRequest.Context context,
        SystemPublicItemRequest.Id key
    ) {
        PublicItemEntity entity = publicItemRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicItemModel result = conversionService.convert(entity, SystemPublicItemModel.class);
        return result;
    }
}
