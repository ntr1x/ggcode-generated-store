package com.example.service.profile.service.profile;

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

import com.example.service.profile.entity.*;
import com.example.service.profile.repository.PublicCustomerRepository;
import com.example.service.profile.model.profile.ProfilePublicCustomerModel;
import com.example.service.profile.request.profile.ProfilePublicCustomerRequest;
import com.example.service.profile.response.profile.ProfilePublicCustomerResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicCustomerService {

    @Qualifier("serviceProfile.publicCustomerRepository")
    private final PublicCustomerRepository publicCustomerRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "public_customer", type = "upserted", source = "service:service_profile", payloadEl = "#result.updated")
    public ProfilePublicCustomerResponse.Upsert upsert(
        ProfilePublicCustomerRequest.Context context,
        ProfilePublicCustomerRequest.Upsert request
    ) {

        Specification<PublicCustomerEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", context.getCustomerId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicCustomerEntity example = PublicCustomerEntity
                .builder()
                .id(context.getCustomerId())
                .build();

        PublicCustomerEntity entity = publicCustomerRepository
                .findOne(specification)
                .orElse(example);

        PublicCustomerEntity.PublicCustomerEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getSurname())
                .ifPresent((value) -> builder.surname(value.orElse(null)));
        Optional.ofNullable(request.getPatronymic())
                .ifPresent((value) -> builder.patronymic(value.orElse(null)));

        entity = publicCustomerRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicCustomerModel updated = conversionService.convert(entity, ProfilePublicCustomerModel.class);

        return ProfilePublicCustomerResponse.Upsert
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public ProfilePublicCustomerModel find(
        ProfilePublicCustomerRequest.Context context,
        ProfilePublicCustomerRequest.Find request
    ) {

        Specification<PublicCustomerEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", context.getCustomerId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicCustomerRepository
                .findOne(specification)
                .map(item -> conversionService.convert(item, ProfilePublicCustomerModel.class))
                .orElse(null);
    }
}
