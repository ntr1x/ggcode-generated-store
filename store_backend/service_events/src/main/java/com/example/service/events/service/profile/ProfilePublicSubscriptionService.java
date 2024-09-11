package com.example.service.events.service.profile;

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

import com.example.service.events.entity.*;
import com.example.service.events.repository.PublicSubscriptionRepository;
import com.example.service.events.model.profile.ProfilePublicSubscriptionModel;
import com.example.service.events.request.profile.ProfilePublicSubscriptionRequest;
import com.example.service.events.response.profile.ProfilePublicSubscriptionResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicSubscriptionService {

    @Qualifier("serviceEvents.publicSubscriptionRepository")
    private final PublicSubscriptionRepository publicSubscriptionRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public ProfilePublicSubscriptionResponse.Create create(
        ProfilePublicSubscriptionRequest.Context context,
        ProfilePublicSubscriptionRequest.Create request
    ) {

        PublicSubscriptionEntity.PublicSubscriptionEntityBuilder builder = PublicSubscriptionEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        PublicSubscriptionEntity entity = publicSubscriptionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicSubscriptionModel created = conversionService.convert(entity, ProfilePublicSubscriptionModel.class);

        return ProfilePublicSubscriptionResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public ProfilePublicSubscriptionResponse.Remove remove(
        ProfilePublicSubscriptionRequest.Context context,
        ProfilePublicSubscriptionRequest.Id key
    ) {
        PublicSubscriptionEntity entity = publicSubscriptionRepository
                .findById(key.getId())
                .orElseThrow();
        
        Validate.create(400, "Bad Request")
                .equals(entity.getCustomerId(), context.getCustomerId());

        ProfilePublicSubscriptionModel removed = conversionService.convert(entity, ProfilePublicSubscriptionModel.class);

        publicSubscriptionRepository.delete(entity);

        return ProfilePublicSubscriptionResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public ProfilePublicSubscriptionResponse.RemoveAll removeAll(
        ProfilePublicSubscriptionRequest.Context context,
        Collection<ProfilePublicSubscriptionRequest.Id> keys
    ) {
        Collection<PublicSubscriptionEntity> entities = publicSubscriptionRepository
                .findAllById(keys.stream().map(ProfilePublicSubscriptionRequest.Id::getId).toList());
        
        for (PublicSubscriptionEntity entity : entities) {
                Validate.create(400, "Bad Request")
                        .equals(entity.getCustomerId(), context.getCustomerId());
        }

        Collection<ProfilePublicSubscriptionModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, ProfilePublicSubscriptionModel.class))
                .toList();

        publicSubscriptionRepository.deleteAll(entities);

        return ProfilePublicSubscriptionResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public ProfilePublicSubscriptionResponse.Update update(
        ProfilePublicSubscriptionRequest.Context context,
        ProfilePublicSubscriptionRequest.Update request
    ) {

        Specification<PublicSubscriptionEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .build();

        PublicSubscriptionEntity entity = publicSubscriptionRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicSubscriptionEntity.PublicSubscriptionEntityBuilder builder = entity.toBuilder();
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicSubscriptionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicSubscriptionModel updated = conversionService.convert(entity, ProfilePublicSubscriptionModel.class);

        return ProfilePublicSubscriptionResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public ProfilePublicSubscriptionResponse.Replace replace(
        ProfilePublicSubscriptionRequest.Context context,
        ProfilePublicSubscriptionRequest.Id key,
        ProfilePublicSubscriptionRequest.Replace request
    ) {
        PublicSubscriptionEntity entity = publicSubscriptionRepository
                .findById(key.getId())
                .orElseThrow();
        
        Validate.create(400, "Bad Request")
                .equals(entity.getCustomerId(), context.getCustomerId());

        ProfilePublicSubscriptionModel removed = conversionService.convert(entity, ProfilePublicSubscriptionModel.class);

        PublicSubscriptionEntity.PublicSubscriptionEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getSessionId())
                .ifPresent((value) -> builder.sessionId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicSubscriptionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicSubscriptionModel created = conversionService.convert(entity, ProfilePublicSubscriptionModel.class);

        return ProfilePublicSubscriptionResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<ProfilePublicSubscriptionModel> select(
        ProfilePublicSubscriptionRequest.Context context,
        ProfilePublicSubscriptionRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicSubscriptionEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .withOptionalMatch("typeId", request.getTypeId())
                    .withOptionalMatch("sessionId", request.getSessionId())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("payload", request.getPayload())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicSubscriptionRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, ProfilePublicSubscriptionModel.class));
    }
    
    @Transactional
    public ProfilePublicSubscriptionModel get(
        ProfilePublicSubscriptionRequest.Context context,
        ProfilePublicSubscriptionRequest.Id key
    ) {
        PublicSubscriptionEntity entity = publicSubscriptionRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicSubscriptionModel result = conversionService.convert(entity, ProfilePublicSubscriptionModel.class);
        return result;
    }
}
