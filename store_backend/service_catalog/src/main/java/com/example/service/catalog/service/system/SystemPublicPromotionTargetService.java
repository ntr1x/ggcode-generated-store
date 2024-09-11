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
import com.example.service.catalog.repository.PublicPromotionTargetRepository;
import com.example.service.catalog.model.system.SystemPublicPromotionTargetModel;
import com.example.service.catalog.request.system.SystemPublicPromotionTargetRequest;
import com.example.service.catalog.response.system.SystemPublicPromotionTargetResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicPromotionTargetService {

    @Qualifier("serviceCatalog.publicPromotionTargetRepository")
    private final PublicPromotionTargetRepository publicPromotionTargetRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicPromotionTargetResponse.Create create(
        SystemPublicPromotionTargetRequest.Context context,
        SystemPublicPromotionTargetRequest.Create request
    ) {

        PublicPromotionTargetEntity.PublicPromotionTargetEntityBuilder builder = PublicPromotionTargetEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getSourceTypeId())
                .ifPresent((value) -> builder.sourceTypeId(value.orElse(null)));
        Optional.ofNullable(request.getPromotionId())
                .ifPresent((value) -> builder.promotionId(value.orElse(null)));
        Optional.ofNullable(request.getCategoryId())
                .ifPresent((value) -> builder.categoryId(value.orElse(null)));
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));

        PublicPromotionTargetEntity entity = publicPromotionTargetRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionTargetModel created = conversionService.convert(entity, SystemPublicPromotionTargetModel.class);

        return SystemPublicPromotionTargetResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionTargetResponse.Remove remove(
        SystemPublicPromotionTargetRequest.Context context,
        SystemPublicPromotionTargetRequest.Id key
    ) {
        PublicPromotionTargetEntity entity = publicPromotionTargetRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionTargetModel removed = conversionService.convert(entity, SystemPublicPromotionTargetModel.class);

        publicPromotionTargetRepository.delete(entity);

        return SystemPublicPromotionTargetResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionTargetResponse.RemoveAll removeAll(
        SystemPublicPromotionTargetRequest.Context context,
        Collection<SystemPublicPromotionTargetRequest.Id> keys
    ) {
        Collection<PublicPromotionTargetEntity> entities = publicPromotionTargetRepository
                .findAllById(keys.stream().map(SystemPublicPromotionTargetRequest.Id::getId).toList());

        Collection<SystemPublicPromotionTargetModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicPromotionTargetModel.class))
                .toList();

        publicPromotionTargetRepository.deleteAll(entities);

        return SystemPublicPromotionTargetResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionTargetResponse.Update update(
        SystemPublicPromotionTargetRequest.Context context,
        SystemPublicPromotionTargetRequest.Update request
    ) {

        Specification<PublicPromotionTargetEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicPromotionTargetEntity entity = publicPromotionTargetRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicPromotionTargetEntity.PublicPromotionTargetEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getSourceTypeId())
                .ifPresent((value) -> builder.sourceTypeId(value.orElse(null)));
        Optional.ofNullable(request.getPromotionId())
                .ifPresent((value) -> builder.promotionId(value.orElse(null)));
        Optional.ofNullable(request.getCategoryId())
                .ifPresent((value) -> builder.categoryId(value.orElse(null)));
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));

        entity = publicPromotionTargetRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionTargetModel updated = conversionService.convert(entity, SystemPublicPromotionTargetModel.class);

        return SystemPublicPromotionTargetResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionTargetResponse.Replace replace(
        SystemPublicPromotionTargetRequest.Context context,
        SystemPublicPromotionTargetRequest.Id key,
        SystemPublicPromotionTargetRequest.Replace request
    ) {
        PublicPromotionTargetEntity entity = publicPromotionTargetRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionTargetModel removed = conversionService.convert(entity, SystemPublicPromotionTargetModel.class);

        PublicPromotionTargetEntity.PublicPromotionTargetEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getSourceTypeId())
                .ifPresent((value) -> builder.sourceTypeId(value.orElse(null)));
        Optional.ofNullable(request.getPromotionId())
                .ifPresent((value) -> builder.promotionId(value.orElse(null)));
        Optional.ofNullable(request.getCategoryId())
                .ifPresent((value) -> builder.categoryId(value.orElse(null)));
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));

        entity = publicPromotionTargetRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionTargetModel created = conversionService.convert(entity, SystemPublicPromotionTargetModel.class);

        return SystemPublicPromotionTargetResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicPromotionTargetModel> select(
        SystemPublicPromotionTargetRequest.Context context,
        SystemPublicPromotionTargetRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicPromotionTargetEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("sourceTypeId", request.getSourceTypeId())
                    .withOptionalMatch("promotionId", request.getPromotionId())
                    .withOptionalMatch("categoryId", request.getCategoryId())
                    .withOptionalMatch("productId", request.getProductId())
                    .withOptionalMatch("regionId", request.getRegionId())
                    .withOptionalMatch("shopId", request.getShopId())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicPromotionTargetRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicPromotionTargetModel.class));
    }
    
    @Transactional
    public SystemPublicPromotionTargetModel get(
        SystemPublicPromotionTargetRequest.Context context,
        SystemPublicPromotionTargetRequest.Id key
    ) {
        PublicPromotionTargetEntity entity = publicPromotionTargetRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionTargetModel result = conversionService.convert(entity, SystemPublicPromotionTargetModel.class);
        return result;
    }
}
