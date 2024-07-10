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
import com.example.service.payments.repository.PublicFavoriteRepository;
import com.example.service.payments.model.profile.ProfilePublicFavoriteModel;
import com.example.service.payments.request.profile.ProfilePublicFavoriteRequest;
import com.example.service.payments.response.profile.ProfilePublicFavoriteResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicFavoriteService {

    @Qualifier("servicePayments.publicFavoriteRepository")
    private final PublicFavoriteRepository publicFavoriteRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public ProfilePublicFavoriteResponse.Create create(
        ProfilePublicFavoriteRequest.Context context,
        ProfilePublicFavoriteRequest.Create request
    ) {

        PublicFavoriteEntity.PublicFavoriteEntityBuilder builder = PublicFavoriteEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));

        PublicFavoriteEntity entity = publicFavoriteRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicFavoriteModel created = conversionService.convert(entity, ProfilePublicFavoriteModel.class);

        return ProfilePublicFavoriteResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public ProfilePublicFavoriteResponse.Remove remove(
        ProfilePublicFavoriteRequest.Context context,
        ProfilePublicFavoriteRequest.Id key
    ) {
        PublicFavoriteEntity entity = publicFavoriteRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicFavoriteModel removed = conversionService.convert(entity, ProfilePublicFavoriteModel.class);

        publicFavoriteRepository.delete(entity);

        return ProfilePublicFavoriteResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public ProfilePublicFavoriteResponse.Update update(
        ProfilePublicFavoriteRequest.Context context,
        ProfilePublicFavoriteRequest.Update request
    ) {

        Specification<PublicFavoriteEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .build();

        PublicFavoriteEntity entity = publicFavoriteRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicFavoriteEntity.PublicFavoriteEntityBuilder builder = entity.toBuilder();
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));

        entity = publicFavoriteRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicFavoriteModel updated = conversionService.convert(entity, ProfilePublicFavoriteModel.class);

        return ProfilePublicFavoriteResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public ProfilePublicFavoriteResponse.Replace replace(
        ProfilePublicFavoriteRequest.Context context,
        ProfilePublicFavoriteRequest.Id key,
        ProfilePublicFavoriteRequest.Replace request
    ) {
        PublicFavoriteEntity entity = publicFavoriteRepository
                .findById(key.getId())
                .orElseThrow();
        
        Validate.create(400, "Bad Request")
                .equals(entity.getCustomerId(), context.getCustomerId());

        ProfilePublicFavoriteModel removed = conversionService.convert(entity, ProfilePublicFavoriteModel.class);

        PublicFavoriteEntity.PublicFavoriteEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getProductId())
                .ifPresent((value) -> builder.productId(value.orElse(null)));

        entity = publicFavoriteRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicFavoriteModel created = conversionService.convert(entity, ProfilePublicFavoriteModel.class);

        return ProfilePublicFavoriteResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<ProfilePublicFavoriteModel> select(
        ProfilePublicFavoriteRequest.Context context,
        ProfilePublicFavoriteRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicFavoriteEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .withOptionalMatch("productId", request.getProductId())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicFavoriteRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, ProfilePublicFavoriteModel.class));
    }
    
    @Transactional
    public ProfilePublicFavoriteModel get(
        ProfilePublicFavoriteRequest.Context context,
        ProfilePublicFavoriteRequest.Id key
    ) {
        PublicFavoriteEntity entity = publicFavoriteRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicFavoriteModel result = conversionService.convert(entity, ProfilePublicFavoriteModel.class);
        return result;
    }
}
