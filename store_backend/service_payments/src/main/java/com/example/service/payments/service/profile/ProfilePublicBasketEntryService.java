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
import com.example.service.payments.repository.PublicBasketEntryRepository;
import com.example.service.payments.model.profile.ProfilePublicBasketEntryModel;
import com.example.service.payments.request.profile.ProfilePublicBasketEntryRequest;
import com.example.service.payments.response.profile.ProfilePublicBasketEntryResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicBasketEntryService {

    @Qualifier("servicePayments.publicBasketEntryRepository")
    private final PublicBasketEntryRepository publicBasketEntryRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public ProfilePublicBasketEntryResponse.Create create(
        ProfilePublicBasketEntryRequest.Context context,
        ProfilePublicBasketEntryRequest.Create request
    ) {
        
        PublicBasketEntity basket = Optional
                .ofNullable(request.getBasketId())
                .flatMap(id -> id)
                .map(id -> entityManager.find(PublicBasketEntity.class, id))
                .orElse(null);
        
        Validate.create(400, "Bad Request")
                .equals(basket.getCustomerId(), context.getCustomerId());

        PublicBasketEntryEntity.PublicBasketEntryEntityBuilder builder = PublicBasketEntryEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getBasketId())
                .ifPresent((value) -> builder.basketId(value.orElse(null)));
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getQuantity())
                .ifPresent((value) -> builder.quantity(value.orElse(null)));

        PublicBasketEntryEntity entity = publicBasketEntryRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicBasketEntryModel created = conversionService.convert(entity, ProfilePublicBasketEntryModel.class);

        return ProfilePublicBasketEntryResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public ProfilePublicBasketEntryResponse.Remove remove(
        ProfilePublicBasketEntryRequest.Context context,
        ProfilePublicBasketEntryRequest.Id key
    ) {
        PublicBasketEntryEntity entity = publicBasketEntryRepository
                .findById(key.getId())
                .orElseThrow();
        
        Validate.create(400, "Bad Request")
                .equals(entity.getBasket().getCustomerId(), context.getCustomerId());

        ProfilePublicBasketEntryModel removed = conversionService.convert(entity, ProfilePublicBasketEntryModel.class);

        publicBasketEntryRepository.delete(entity);

        return ProfilePublicBasketEntryResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public ProfilePublicBasketEntryResponse.Update update(
        ProfilePublicBasketEntryRequest.Context context,
        ProfilePublicBasketEntryRequest.Update request
    ) {
        
        PublicBasketEntity basket = Optional
                .ofNullable(request.getBasketId())
                .flatMap(id -> id)
                .map(id -> entityManager.find(PublicBasketEntity.class, id))
                .orElse(null);
        
        Validate.create(400, "Bad Request")
                .equals(basket.getCustomerId(), context.getCustomerId());

        Specification<PublicBasketEntryEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromValue("id", request.getId()),
                    predicateFactory.fromValue("basketId", basket.getId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        PublicBasketEntryEntity entity = publicBasketEntryRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicBasketEntryEntity.PublicBasketEntryEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getBasketId())
                .ifPresent((value) -> builder.basketId(value.orElse(null)));
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getQuantity())
                .ifPresent((value) -> builder.quantity(value.orElse(null)));

        entity = publicBasketEntryRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicBasketEntryModel updated = conversionService.convert(entity, ProfilePublicBasketEntryModel.class);

        return ProfilePublicBasketEntryResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public ProfilePublicBasketEntryResponse.Replace replace(
        ProfilePublicBasketEntryRequest.Context context,
        ProfilePublicBasketEntryRequest.Id key,
        ProfilePublicBasketEntryRequest.Replace request
    ) {
        PublicBasketEntryEntity entity = publicBasketEntryRepository
                .findById(key.getId())
                .orElseThrow();
        
        PublicBasketEntity basket = Optional
                .ofNullable(request.getBasketId())
                .flatMap(id -> id)
                .map(id -> entityManager.find(PublicBasketEntity.class, id))
                .orElse(null);
        
        Validate.create(400, "Bad Request")
                .equals(basket.getCustomerId(), context.getCustomerId());

        ProfilePublicBasketEntryModel removed = conversionService.convert(entity, ProfilePublicBasketEntryModel.class);

        PublicBasketEntryEntity.PublicBasketEntryEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getBasketId())
                .ifPresent((value) -> builder.basketId(value.orElse(null)));
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));
        Optional.ofNullable(request.getQuantity())
                .ifPresent((value) -> builder.quantity(value.orElse(null)));

        entity = publicBasketEntryRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicBasketEntryModel created = conversionService.convert(entity, ProfilePublicBasketEntryModel.class);

        return ProfilePublicBasketEntryResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<ProfilePublicBasketEntryModel> select(
        ProfilePublicBasketEntryRequest.Context context,
        ProfilePublicBasketEntryRequest.Select request,
        Pageable pageable
    ) {
        
        PublicBasketEntity basket = Optional
                .ofNullable(request.getBasketId())
                .flatMap(id -> id)
                .map(id -> entityManager.find(PublicBasketEntity.class, id))
                .orElse(null);
        
        Validate.create(400, "Bad Request")
                .equals(basket.getCustomerId(), context.getCustomerId());

        Specification<PublicBasketEntryEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("basketId", request.getBasketId()),
                    predicateFactory.fromOptional("productId", request.getProductId()),
                    predicateFactory.fromOptional("quantity", request.getQuantity()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicBasketEntryRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, ProfilePublicBasketEntryModel.class));
    }
    
    @Transactional
    public ProfilePublicBasketEntryModel get(
        ProfilePublicBasketEntryRequest.Context context,
        ProfilePublicBasketEntryRequest.Id key
    ) {
        PublicBasketEntryEntity entity = publicBasketEntryRepository
                .findById(key.getId())
                .orElseThrow();
        
        Validate.create(400, "Bad Request")
                .equals(entity.getBasket().getCustomerId(), context.getCustomerId());

        ProfilePublicBasketEntryModel result = conversionService.convert(entity, ProfilePublicBasketEntryModel.class);
        return result;
    }
}
