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
import com.example.service.payments.repository.PublicPaymentRepository;
import com.example.service.payments.model.profile.ProfilePublicPaymentModel;
import com.example.service.payments.request.profile.ProfilePublicPaymentRequest;
import com.example.service.payments.response.profile.ProfilePublicPaymentResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
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

        Specification<PublicPaymentEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromValue("customerId", context.getCustomerId()),
                    predicateFactory.fromOptional("orderId", request.getOrderId()),
                    predicateFactory.fromOptional("paymentStatusId", request.getPaymentStatusId()),
                    predicateFactory.fromOptional("paymentTypeId", request.getPaymentTypeId()),
                    predicateFactory.fromOptional("value", request.getValue()),
                    predicateFactory.fromOptional("createdAt", request.getCreatedAt()),
                    predicateFactory.fromOptional("updatedAt", request.getUpdatedAt()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

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
