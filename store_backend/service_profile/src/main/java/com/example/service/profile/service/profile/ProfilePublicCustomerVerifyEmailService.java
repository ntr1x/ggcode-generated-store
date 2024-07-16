package com.example.service.profile.service.profile;

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

import com.example.service.profile.entity.*;
import com.example.service.profile.repository.PublicCustomerVerifyEmailRepository;
import com.example.service.profile.model.profile.ProfilePublicCustomerVerifyEmailModel;
import com.example.service.profile.request.profile.ProfilePublicCustomerVerifyEmailRequest;
import com.example.service.profile.response.profile.ProfilePublicCustomerVerifyEmailResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicCustomerVerifyEmailService {

    @Qualifier("serviceProfile.publicCustomerVerifyEmailRepository")
    private final PublicCustomerVerifyEmailRepository publicCustomerVerifyEmailRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "verify_email", type = "created", source = "service:service_profile", payloadEl = "#result.created")
    public ProfilePublicCustomerVerifyEmailResponse.Create create(
        ProfilePublicCustomerVerifyEmailRequest.Context context,
        ProfilePublicCustomerVerifyEmailRequest.Create request
    ) {

        PublicCustomerVerifyEmailEntity.PublicCustomerVerifyEmailEntityBuilder builder = PublicCustomerVerifyEmailEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getEmail())
                .ifPresent((value) -> builder.email(value.orElse(null)));
        builder.secret(evaluatorService.evaluate("@generatorService.randomString(12)", java.lang.String.class));
        builder.isConfirmed(false);

        PublicCustomerVerifyEmailEntity entity = publicCustomerVerifyEmailRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicCustomerVerifyEmailModel created = conversionService.convert(entity, ProfilePublicCustomerVerifyEmailModel.class);

        return ProfilePublicCustomerVerifyEmailResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public ProfilePublicCustomerVerifyEmailResponse.Remove remove(
        ProfilePublicCustomerVerifyEmailRequest.Context context,
        ProfilePublicCustomerVerifyEmailRequest.Id key
    ) {
        PublicCustomerVerifyEmailEntity entity = publicCustomerVerifyEmailRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicCustomerVerifyEmailModel removed = conversionService.convert(entity, ProfilePublicCustomerVerifyEmailModel.class);

        publicCustomerVerifyEmailRepository.delete(entity);

        return ProfilePublicCustomerVerifyEmailResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public ProfilePublicCustomerVerifyEmailResponse.RemoveAll removeAll(
        ProfilePublicCustomerVerifyEmailRequest.Context context,
        Collection<ProfilePublicCustomerVerifyEmailRequest.Id> keys
    ) {
        Collection<PublicCustomerVerifyEmailEntity> entities = publicCustomerVerifyEmailRepository
                .findAllById(keys.stream().map(ProfilePublicCustomerVerifyEmailRequest.Id::getId).toList());

        Collection<ProfilePublicCustomerVerifyEmailModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, ProfilePublicCustomerVerifyEmailModel.class))
                .toList();

        publicCustomerVerifyEmailRepository.deleteAll(entities);

        return ProfilePublicCustomerVerifyEmailResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "update_email", type = "updated", source = "service:service_profile", payloadEl = "#result.updated")
    public ProfilePublicCustomerVerifyEmailResponse.Update update(
        ProfilePublicCustomerVerifyEmailRequest.Context context,
        ProfilePublicCustomerVerifyEmailRequest.Update request
    ) {
        
        Validate.create(400, "Property 'id' is required'")
                .notNull(request.getId());
        Validate.create(400, "Property 'secret' is required'")
                .notNull(request.getSecret());

        Specification<PublicCustomerVerifyEmailEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .withValueMatch("isConfirmed", false)
                    .withOptionalMatch("secret", request.getSecret())
                    .build();

        PublicCustomerVerifyEmailEntity entity = publicCustomerVerifyEmailRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicCustomerVerifyEmailEntity.PublicCustomerVerifyEmailEntityBuilder builder = entity.toBuilder();
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getSecret())
                .ifPresent((value) -> builder.secret(value.orElse(null)));
        builder.isConfirmed(true);

        entity = publicCustomerVerifyEmailRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicCustomerVerifyEmailModel updated = conversionService.convert(entity, ProfilePublicCustomerVerifyEmailModel.class);

        return ProfilePublicCustomerVerifyEmailResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public Page<ProfilePublicCustomerVerifyEmailModel> select(
        ProfilePublicCustomerVerifyEmailRequest.Context context,
        ProfilePublicCustomerVerifyEmailRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicCustomerVerifyEmailEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .withOptionalMatch("email", request.getEmail())
                    .withOptionalMatch("isConfirmed", request.getIsConfirmed())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicCustomerVerifyEmailRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, ProfilePublicCustomerVerifyEmailModel.class));
    }
    
    @Transactional
    public ProfilePublicCustomerVerifyEmailModel get(
        ProfilePublicCustomerVerifyEmailRequest.Context context,
        ProfilePublicCustomerVerifyEmailRequest.Id key
    ) {
        PublicCustomerVerifyEmailEntity entity = publicCustomerVerifyEmailRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicCustomerVerifyEmailModel result = conversionService.convert(entity, ProfilePublicCustomerVerifyEmailModel.class);
        return result;
    }
}
