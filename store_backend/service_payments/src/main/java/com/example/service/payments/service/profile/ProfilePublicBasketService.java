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
import com.example.service.payments.repository.PublicBasketRepository;
import com.example.service.payments.model.profile.ProfilePublicBasketModel;
import com.example.service.payments.request.profile.ProfilePublicBasketRequest;
import com.example.service.payments.response.profile.ProfilePublicBasketResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicBasketService {

    @Qualifier("servicePayments.publicBasketRepository")
    private final PublicBasketRepository publicBasketRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public ProfilePublicBasketResponse.Create create(
        ProfilePublicBasketRequest.Context context,
        ProfilePublicBasketRequest.Create request
    ) {

        PublicBasketEntity.PublicBasketEntityBuilder builder = PublicBasketEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getAgentId())
                .ifPresent((value) -> builder.agentId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));

        PublicBasketEntity entity = publicBasketRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicBasketModel created = conversionService.convert(entity, ProfilePublicBasketModel.class);

        return ProfilePublicBasketResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public ProfilePublicBasketResponse.Remove remove(
        ProfilePublicBasketRequest.Context context,
        ProfilePublicBasketRequest.Id key
    ) {
        PublicBasketEntity entity = publicBasketRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicBasketModel removed = conversionService.convert(entity, ProfilePublicBasketModel.class);

        publicBasketRepository.delete(entity);

        return ProfilePublicBasketResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public ProfilePublicBasketResponse.Update update(
        ProfilePublicBasketRequest.Context context,
        ProfilePublicBasketRequest.Update request
    ) {

        Specification<PublicBasketEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
                    predicateFactory.fromValue("customerId", context.getCustomerId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicBasketEntity entity = publicBasketRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicBasketEntity.PublicBasketEntityBuilder builder = entity.toBuilder();
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getAgentId())
                .ifPresent((value) -> builder.agentId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));

        entity = publicBasketRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicBasketModel updated = conversionService.convert(entity, ProfilePublicBasketModel.class);

        return ProfilePublicBasketResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public ProfilePublicBasketResponse.Replace replace(
        ProfilePublicBasketRequest.Context context,
        ProfilePublicBasketRequest.Id key,
        ProfilePublicBasketRequest.Replace request
    ) {
        PublicBasketEntity entity = publicBasketRepository
                .findById(key.getId())
                .orElseThrow();
        
        Validate.create(400, "Bad Request")
                .equals(entity.getCustomerId(), context.getCustomerId());

        ProfilePublicBasketModel removed = conversionService.convert(entity, ProfilePublicBasketModel.class);

        PublicBasketEntity.PublicBasketEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getAgentId())
                .ifPresent((value) -> builder.agentId(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));

        entity = publicBasketRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicBasketModel created = conversionService.convert(entity, ProfilePublicBasketModel.class);

        return ProfilePublicBasketResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<ProfilePublicBasketModel> select(
        ProfilePublicBasketRequest.Context context,
        ProfilePublicBasketRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicBasketEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromValue("customerId", context.getCustomerId()),
                    predicateFactory.fromOptional("agentId", request.getAgentId()),
                    predicateFactory.fromOptional("regionId", request.getRegionId()),
                    predicateFactory.fromOptional("shopId", request.getShopId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicBasketRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, ProfilePublicBasketModel.class));
    }
    
    @Transactional
    public ProfilePublicBasketModel get(
        ProfilePublicBasketRequest.Context context,
        ProfilePublicBasketRequest.Id key
    ) {
        PublicBasketEntity entity = publicBasketRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicBasketModel result = conversionService.convert(entity, ProfilePublicBasketModel.class);
        return result;
    }
}
