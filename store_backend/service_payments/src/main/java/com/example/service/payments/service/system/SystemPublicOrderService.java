package com.example.service.payments.service.system;

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

import com.example.service.payments.entity.*;
import com.example.service.payments.repository.PublicOrderRepository;
import com.example.service.payments.model.system.SystemPublicOrderModel;
import com.example.service.payments.request.system.SystemPublicOrderRequest;
import com.example.service.payments.response.system.SystemPublicOrderResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicOrderService {

    @Qualifier("servicePayments.publicOrderRepository")
    private final PublicOrderRepository publicOrderRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicOrderResponse.Create create(
        SystemPublicOrderRequest.Context context,
        SystemPublicOrderRequest.Create request
    ) {

        PublicOrderEntity.PublicOrderEntityBuilder builder = PublicOrderEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getSourceTypeId())
                .ifPresent((value) -> builder.sourceTypeId(value.orElse(null)));
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getBasketId())
                .ifPresent((value) -> builder.basketId(value.orElse(null)));
        Optional.ofNullable(request.getAgentId())
                .ifPresent((value) -> builder.agentId(value.orElse(null)));
        Optional.ofNullable(request.getOrderTypeId())
                .ifPresent((value) -> builder.orderTypeId(value.orElse(null)));
        Optional.ofNullable(request.getOrderStatusId())
                .ifPresent((value) -> builder.orderStatusId(value.orElse(null)));
        Optional.ofNullable(request.getPrice())
                .ifPresent((value) -> builder.price(value.orElse(null)));
        Optional.ofNullable(request.getSpend())
                .ifPresent((value) -> builder.spend(value.orElse(null)));
        Optional.ofNullable(request.getBonus())
                .ifPresent((value) -> builder.bonus(value.orElse(null)));
        Optional.ofNullable(request.getDiscount())
                .ifPresent((value) -> builder.discount(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        PublicOrderEntity entity = publicOrderRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderModel created = conversionService.convert(entity, SystemPublicOrderModel.class);

        return SystemPublicOrderResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderResponse.Remove remove(
        SystemPublicOrderRequest.Context context,
        SystemPublicOrderRequest.Id key
    ) {
        PublicOrderEntity entity = publicOrderRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderModel removed = conversionService.convert(entity, SystemPublicOrderModel.class);

        publicOrderRepository.delete(entity);

        return SystemPublicOrderResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderResponse.Update update(
        SystemPublicOrderRequest.Context context,
        SystemPublicOrderRequest.Update request
    ) {

        Specification<PublicOrderEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicOrderEntity entity = publicOrderRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicOrderEntity.PublicOrderEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getSourceTypeId())
                .ifPresent((value) -> builder.sourceTypeId(value.orElse(null)));
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getBasketId())
                .ifPresent((value) -> builder.basketId(value.orElse(null)));
        Optional.ofNullable(request.getAgentId())
                .ifPresent((value) -> builder.agentId(value.orElse(null)));
        Optional.ofNullable(request.getOrderTypeId())
                .ifPresent((value) -> builder.orderTypeId(value.orElse(null)));
        Optional.ofNullable(request.getOrderStatusId())
                .ifPresent((value) -> builder.orderStatusId(value.orElse(null)));
        Optional.ofNullable(request.getPrice())
                .ifPresent((value) -> builder.price(value.orElse(null)));
        Optional.ofNullable(request.getSpend())
                .ifPresent((value) -> builder.spend(value.orElse(null)));
        Optional.ofNullable(request.getBonus())
                .ifPresent((value) -> builder.bonus(value.orElse(null)));
        Optional.ofNullable(request.getDiscount())
                .ifPresent((value) -> builder.discount(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicOrderRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderModel updated = conversionService.convert(entity, SystemPublicOrderModel.class);

        return SystemPublicOrderResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicOrderResponse.Replace replace(
        SystemPublicOrderRequest.Context context,
        SystemPublicOrderRequest.Id key,
        SystemPublicOrderRequest.Replace request
    ) {
        PublicOrderEntity entity = publicOrderRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderModel removed = conversionService.convert(entity, SystemPublicOrderModel.class);

        PublicOrderEntity.PublicOrderEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getSourceTypeId())
                .ifPresent((value) -> builder.sourceTypeId(value.orElse(null)));
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getBasketId())
                .ifPresent((value) -> builder.basketId(value.orElse(null)));
        Optional.ofNullable(request.getAgentId())
                .ifPresent((value) -> builder.agentId(value.orElse(null)));
        Optional.ofNullable(request.getOrderTypeId())
                .ifPresent((value) -> builder.orderTypeId(value.orElse(null)));
        Optional.ofNullable(request.getOrderStatusId())
                .ifPresent((value) -> builder.orderStatusId(value.orElse(null)));
        Optional.ofNullable(request.getPrice())
                .ifPresent((value) -> builder.price(value.orElse(null)));
        Optional.ofNullable(request.getSpend())
                .ifPresent((value) -> builder.spend(value.orElse(null)));
        Optional.ofNullable(request.getBonus())
                .ifPresent((value) -> builder.bonus(value.orElse(null)));
        Optional.ofNullable(request.getDiscount())
                .ifPresent((value) -> builder.discount(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicOrderRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicOrderModel created = conversionService.convert(entity, SystemPublicOrderModel.class);

        return SystemPublicOrderResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicOrderModel> select(
        SystemPublicOrderRequest.Context context,
        SystemPublicOrderRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicOrderEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("sourceTypeId", request.getSourceTypeId())
                    .withOptionalMatch("customerId", request.getCustomerId())
                    .withOptionalMatch("regionId", request.getRegionId())
                    .withOptionalMatch("shopId", request.getShopId())
                    .withOptionalMatch("basketId", request.getBasketId())
                    .withOptionalMatch("agentId", request.getAgentId())
                    .withOptionalMatch("orderTypeId", request.getOrderTypeId())
                    .withOptionalMatch("orderStatusId", request.getOrderStatusId())
                    .withOptionalMatch("price", request.getPrice())
                    .withOptionalMatch("spend", request.getSpend())
                    .withOptionalMatch("bonus", request.getBonus())
                    .withOptionalMatch("discount", request.getDiscount())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("updatedAt", request.getUpdatedAt())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicOrderRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicOrderModel.class));
    }
    
    @Transactional
    public SystemPublicOrderModel get(
        SystemPublicOrderRequest.Context context,
        SystemPublicOrderRequest.Id key
    ) {
        PublicOrderEntity entity = publicOrderRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicOrderModel result = conversionService.convert(entity, SystemPublicOrderModel.class);
        return result;
    }
}
