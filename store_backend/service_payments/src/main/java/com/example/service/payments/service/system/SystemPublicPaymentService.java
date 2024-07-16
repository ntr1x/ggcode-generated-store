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
import com.example.service.payments.repository.PublicPaymentRepository;
import com.example.service.payments.model.system.SystemPublicPaymentModel;
import com.example.service.payments.request.system.SystemPublicPaymentRequest;
import com.example.service.payments.response.system.SystemPublicPaymentResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
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
    public SystemPublicPaymentResponse.RemoveAll removeAll(
        SystemPublicPaymentRequest.Context context,
        Collection<SystemPublicPaymentRequest.Id> keys
    ) {
        Collection<PublicPaymentEntity> entities = publicPaymentRepository
                .findAllById(keys.stream().map(SystemPublicPaymentRequest.Id::getId).toList());

        Collection<SystemPublicPaymentModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicPaymentModel.class))
                .toList();

        publicPaymentRepository.deleteAll(entities);

        return SystemPublicPaymentResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicPaymentResponse.Update update(
        SystemPublicPaymentRequest.Context context,
        SystemPublicPaymentRequest.Update request
    ) {

        Specification<PublicPaymentEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicPaymentEntity entity = publicPaymentRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

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

        Specification<PublicPaymentEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("customerId", request.getCustomerId())
                    .withOptionalMatch("orderId", request.getOrderId())
                    .withOptionalMatch("paymentStatusId", request.getPaymentStatusId())
                    .withOptionalMatch("paymentTypeId", request.getPaymentTypeId())
                    .withOptionalMatch("value", request.getValue())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("updatedAt", request.getUpdatedAt())
                    .withOptionalPathMatch("order.id", request, java.util.UUID.class, "order.id")
                    .withOptionalPathMatch("order.sourceTypeId", request, java.lang.Integer.class, "order.sourceTypeId")
                    .withOptionalPathMatch("order.customerId", request, java.util.UUID.class, "order.customerId")
                    .withOptionalPathMatch("order.regionId", request, java.util.UUID.class, "order.regionId")
                    .withOptionalPathMatch("order.shopId", request, java.util.UUID.class, "order.shopId")
                    .withOptionalPathMatch("order.basketId", request, java.util.UUID.class, "order.basketId")
                    .withOptionalPathMatch("order.agentId", request, java.util.UUID.class, "order.agentId")
                    .withOptionalPathMatch("order.orderTypeId", request, java.lang.Integer.class, "order.orderTypeId")
                    .withOptionalPathMatch("order.orderStatusId", request, java.lang.Integer.class, "order.orderStatusId")
                    .withOptionalPathMatch("order.price", request, java.math.BigDecimal.class, "order.price")
                    .withOptionalPathMatch("order.spend", request, java.math.BigDecimal.class, "order.spend")
                    .withOptionalPathMatch("order.bonus", request, java.math.BigDecimal.class, "order.bonus")
                    .withOptionalPathMatch("order.discount", request, java.math.BigDecimal.class, "order.discount")
                    .withOptionalPathMatch("order.createdAt", request, java.time.LocalDateTime.class, "order.createdAt")
                    .withOptionalPathMatch("order.updatedAt", request, java.time.LocalDateTime.class, "order.updatedAt")
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

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
