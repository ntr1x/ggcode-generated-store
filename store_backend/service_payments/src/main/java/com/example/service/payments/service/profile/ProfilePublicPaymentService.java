package com.example.service.payments.service.profile;

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
import com.example.service.payments.model.profile.ProfilePublicPaymentModel;
import com.example.service.payments.request.profile.ProfilePublicPaymentRequest;
import com.example.service.payments.response.profile.ProfilePublicPaymentResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicPaymentService {

    @Qualifier("servicePayments.publicPaymentRepository")
    private final PublicPaymentRepository publicPaymentRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public Page<ProfilePublicPaymentModel> select(
        ProfilePublicPaymentRequest.Context context,
        ProfilePublicPaymentRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicPaymentEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .withOptionalMatch("orderId", request.getOrderId())
                    .withOptionalMatch("paymentStatusId", request.getPaymentStatusId())
                    .withOptionalMatch("paymentTypeId", request.getPaymentTypeId())
                    .withOptionalMatch("value", request.getValue())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("updatedAt", request.getUpdatedAt())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicPaymentRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, ProfilePublicPaymentModel.class));
    }
    
    @Transactional
    public ProfilePublicPaymentModel get(
        ProfilePublicPaymentRequest.Context context,
        ProfilePublicPaymentRequest.Id key
    ) {
        PublicPaymentEntity entity = publicPaymentRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicPaymentModel result = conversionService.convert(entity, ProfilePublicPaymentModel.class);
        return result;
    }
}
