package com.example.service.payments.service.profile;

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
import com.example.service.payments.repository.PublicOrderRepository;
import com.example.service.payments.model.profile.ProfilePublicOrderModel;
import com.example.service.payments.request.profile.ProfilePublicOrderRequest;
import com.example.service.payments.response.profile.ProfilePublicOrderResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicOrderService {

    @Qualifier("servicePayments.publicOrderRepository")
    private final PublicOrderRepository publicOrderRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public ProfilePublicOrderResponse.Create create(
        ProfilePublicOrderRequest.Context context,
        ProfilePublicOrderRequest.Create request
    ) {

        PublicOrderEntity.PublicOrderEntityBuilder builder = PublicOrderEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getSourceTypeId())
                .ifPresent((value) -> builder.sourceTypeId(value.orElse(null)));
        builder.customerId(context.getCustomerId());
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

        ProfilePublicOrderModel created = conversionService.convert(entity, ProfilePublicOrderModel.class);

        return ProfilePublicOrderResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<ProfilePublicOrderModel> select(
        ProfilePublicOrderRequest.Context context,
        ProfilePublicOrderRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicOrderEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("sourceTypeId", request.getSourceTypeId()),
                    predicateFactory.fromValue("customerId", context.getCustomerId()),
                    predicateFactory.fromOptional("regionId", request.getRegionId()),
                    predicateFactory.fromOptional("shopId", request.getShopId()),
                    predicateFactory.fromOptional("basketId", request.getBasketId()),
                    predicateFactory.fromOptional("agentId", request.getAgentId()),
                    predicateFactory.fromOptional("orderTypeId", request.getOrderTypeId()),
                    predicateFactory.fromOptional("orderStatusId", request.getOrderStatusId()),
                    predicateFactory.fromOptional("price", request.getPrice()),
                    predicateFactory.fromOptional("spend", request.getSpend()),
                    predicateFactory.fromOptional("bonus", request.getBonus()),
                    predicateFactory.fromOptional("discount", request.getDiscount()),
                    predicateFactory.fromOptional("createdAt", request.getCreatedAt()),
                    predicateFactory.fromOptional("updatedAt", request.getUpdatedAt()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicOrderRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, ProfilePublicOrderModel.class));
    }
    
    @Transactional
    public ProfilePublicOrderModel get(
        ProfilePublicOrderRequest.Context context,
        ProfilePublicOrderRequest.Id key
    ) {
        PublicOrderEntity entity = publicOrderRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicOrderModel result = conversionService.convert(entity, ProfilePublicOrderModel.class);
        return result;
    }
}
