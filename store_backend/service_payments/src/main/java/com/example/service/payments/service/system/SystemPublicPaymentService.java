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
import com.example.service.payments.repository.PublicPaymentRepository;
import com.example.service.payments.model.system.SystemPublicPaymentModel;
import com.example.service.payments.request.system.SystemPublicPaymentRequest;
import com.example.service.payments.response.system.SystemPublicPaymentResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicPaymentService {

    @Qualifier("servicePayments.publicPaymentRepository")
    private final PublicPaymentRepository publicPaymentRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicPaymentResponse.Create create(
        SystemPublicPaymentRequest.Context context,
        SystemPublicPaymentRequest.Create request
    ) {

        PublicPaymentEntity.PublicPaymentEntityBuilder builder = PublicPaymentEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getOrderId())
                .ifPresent((value) -> builder.orderId(value.orElse(null)));
        Optional.ofNullable(request.getPaymentStatusId())
                .ifPresent((value) -> builder.paymentStatusId(value.orElse(null)));
        Optional.ofNullable(request.getPaymentTypeId())
                .ifPresent((value) -> builder.paymentTypeId(value.orElse(null)));
        Optional.ofNullable(request.getValue())
                .ifPresent((value) -> builder.value(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        PublicPaymentEntity entity = publicPaymentRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPaymentModel created = conversionService.convert(entity, SystemPublicPaymentModel.class);

        return SystemPublicPaymentResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicPaymentResponse.Remove remove(
        SystemPublicPaymentRequest.Context context,
        SystemPublicPaymentRequest.Id key
    ) {
        PublicPaymentEntity entity = publicPaymentRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPaymentModel removed = conversionService.convert(entity, SystemPublicPaymentModel.class);

        publicPaymentRepository.delete(entity);

        return SystemPublicPaymentResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicPaymentResponse.Update update(
        SystemPublicPaymentRequest.Context context,
        SystemPublicPaymentRequest.Update request
    ) {

        Specification<PublicPaymentEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicPaymentEntity entity = publicPaymentRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicPaymentEntity.PublicPaymentEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getOrderId())
                .ifPresent((value) -> builder.orderId(value.orElse(null)));
        Optional.ofNullable(request.getPaymentStatusId())
                .ifPresent((value) -> builder.paymentStatusId(value.orElse(null)));
        Optional.ofNullable(request.getPaymentTypeId())
                .ifPresent((value) -> builder.paymentTypeId(value.orElse(null)));
        Optional.ofNullable(request.getValue())
                .ifPresent((value) -> builder.value(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicPaymentRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPaymentModel updated = conversionService.convert(entity, SystemPublicPaymentModel.class);

        return SystemPublicPaymentResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicPaymentResponse.Replace replace(
        SystemPublicPaymentRequest.Context context,
        SystemPublicPaymentRequest.Id key,
        SystemPublicPaymentRequest.Replace request
    ) {
        PublicPaymentEntity entity = publicPaymentRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPaymentModel removed = conversionService.convert(entity, SystemPublicPaymentModel.class);

        PublicPaymentEntity.PublicPaymentEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getOrderId())
                .ifPresent((value) -> builder.orderId(value.orElse(null)));
        Optional.ofNullable(request.getPaymentStatusId())
                .ifPresent((value) -> builder.paymentStatusId(value.orElse(null)));
        Optional.ofNullable(request.getPaymentTypeId())
                .ifPresent((value) -> builder.paymentTypeId(value.orElse(null)));
        Optional.ofNullable(request.getValue())
                .ifPresent((value) -> builder.value(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicPaymentRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPaymentModel created = conversionService.convert(entity, SystemPublicPaymentModel.class);

        return SystemPublicPaymentResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicPaymentModel> select(
        SystemPublicPaymentRequest.Context context,
        SystemPublicPaymentRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicPaymentEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("customerId", request.getCustomerId()),
                    predicateFactory.fromOptional("orderId", request.getOrderId()),
                    predicateFactory.fromOptional("paymentStatusId", request.getPaymentStatusId()),
                    predicateFactory.fromOptional("paymentTypeId", request.getPaymentTypeId()),
                    predicateFactory.fromOptional("value", request.getValue()),
                    predicateFactory.fromOptional("createdAt", request.getCreatedAt()),
                    predicateFactory.fromOptional("updatedAt", request.getUpdatedAt()),
                    predicateFactory.fromOptionalPath("order.id", request, java.util.UUID.class, "order.id"),
                    predicateFactory.fromOptionalPath("order.sourceTypeId", request, java.lang.Integer.class, "order.sourceTypeId"),
                    predicateFactory.fromOptionalPath("order.customerId", request, java.util.UUID.class, "order.customerId"),
                    predicateFactory.fromOptionalPath("order.regionId", request, java.util.UUID.class, "order.regionId"),
                    predicateFactory.fromOptionalPath("order.shopId", request, java.util.UUID.class, "order.shopId"),
                    predicateFactory.fromOptionalPath("order.basketId", request, java.util.UUID.class, "order.basketId"),
                    predicateFactory.fromOptionalPath("order.agentId", request, java.util.UUID.class, "order.agentId"),
                    predicateFactory.fromOptionalPath("order.orderTypeId", request, java.lang.Integer.class, "order.orderTypeId"),
                    predicateFactory.fromOptionalPath("order.orderStatusId", request, java.lang.Integer.class, "order.orderStatusId"),
                    predicateFactory.fromOptionalPath("order.price", request, java.math.BigDecimal.class, "order.price"),
                    predicateFactory.fromOptionalPath("order.spend", request, java.math.BigDecimal.class, "order.spend"),
                    predicateFactory.fromOptionalPath("order.bonus", request, java.math.BigDecimal.class, "order.bonus"),
                    predicateFactory.fromOptionalPath("order.discount", request, java.math.BigDecimal.class, "order.discount"),
                    predicateFactory.fromOptionalPath("order.createdAt", request, java.time.LocalDateTime.class, "order.createdAt"),
                    predicateFactory.fromOptionalPath("order.updatedAt", request, java.time.LocalDateTime.class, "order.updatedAt"),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicPaymentRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicPaymentModel.class));
    }
    
    @Transactional
    public SystemPublicPaymentModel get(
        SystemPublicPaymentRequest.Context context,
        SystemPublicPaymentRequest.Id key
    ) {
        PublicPaymentEntity entity = publicPaymentRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPaymentModel result = conversionService.convert(entity, SystemPublicPaymentModel.class);
        return result;
    }
}
