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
import com.example.service.profile.repository.PublicCustomerVerifyPhoneRepository;
import com.example.service.profile.model.profile.ProfilePublicCustomerVerifyPhoneModel;
import com.example.service.profile.request.profile.ProfilePublicCustomerVerifyPhoneRequest;
import com.example.service.profile.response.profile.ProfilePublicCustomerVerifyPhoneResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilePublicCustomerVerifyPhoneService {

    @Qualifier("serviceProfile.publicCustomerVerifyPhoneRepository")
    private final PublicCustomerVerifyPhoneRepository publicCustomerVerifyPhoneRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "verify_phone", type = "created", source = "service:service_profile", payloadEl = "#result.created")
    public ProfilePublicCustomerVerifyPhoneResponse.Create create(
        ProfilePublicCustomerVerifyPhoneRequest.Context context,
        ProfilePublicCustomerVerifyPhoneRequest.Create request
    ) {

        PublicCustomerVerifyPhoneEntity.PublicCustomerVerifyPhoneEntityBuilder builder = PublicCustomerVerifyPhoneEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getPhone())
                .ifPresent((value) -> builder.phone(value.orElse(null)));
        builder.secret(evaluatorService.evaluate("@generatorService.randomInt(1000, 9999)", java.lang.Integer.class));
        builder.isConfirmed(false);

        PublicCustomerVerifyPhoneEntity entity = publicCustomerVerifyPhoneRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicCustomerVerifyPhoneModel created = conversionService.convert(entity, ProfilePublicCustomerVerifyPhoneModel.class);

        return ProfilePublicCustomerVerifyPhoneResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public ProfilePublicCustomerVerifyPhoneResponse.Remove remove(
        ProfilePublicCustomerVerifyPhoneRequest.Context context,
        ProfilePublicCustomerVerifyPhoneRequest.Id key
    ) {
        PublicCustomerVerifyPhoneEntity entity = publicCustomerVerifyPhoneRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicCustomerVerifyPhoneModel removed = conversionService.convert(entity, ProfilePublicCustomerVerifyPhoneModel.class);

        publicCustomerVerifyPhoneRepository.delete(entity);

        return ProfilePublicCustomerVerifyPhoneResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public ProfilePublicCustomerVerifyPhoneResponse.RemoveAll removeAll(
        ProfilePublicCustomerVerifyPhoneRequest.Context context,
        Collection<ProfilePublicCustomerVerifyPhoneRequest.Id> keys
    ) {
        Collection<PublicCustomerVerifyPhoneEntity> entities = publicCustomerVerifyPhoneRepository
                .findAllById(keys.stream().map(ProfilePublicCustomerVerifyPhoneRequest.Id::getId).toList());

        Collection<ProfilePublicCustomerVerifyPhoneModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, ProfilePublicCustomerVerifyPhoneModel.class))
                .toList();

        publicCustomerVerifyPhoneRepository.deleteAll(entities);

        return ProfilePublicCustomerVerifyPhoneResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "update_phone", type = "updated", source = "service:service_profile", payloadEl = "#result.updated")
    public ProfilePublicCustomerVerifyPhoneResponse.Update update(
        ProfilePublicCustomerVerifyPhoneRequest.Context context,
        ProfilePublicCustomerVerifyPhoneRequest.Update request
    ) {
        
        Validate.create(400, "Property 'id' is required'")
                .notNull(request.getId());
        Validate.create(400, "Property 'secret' is required'")
                .notNull(request.getSecret());

        Specification<PublicCustomerVerifyPhoneEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .withValueMatch("isConfirmed", false)
                    .withOptionalMatch("secret", request.getSecret())
                    .build();

        PublicCustomerVerifyPhoneEntity entity = publicCustomerVerifyPhoneRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicCustomerVerifyPhoneEntity.PublicCustomerVerifyPhoneEntityBuilder builder = entity.toBuilder();
        
        builder.customerId(context.getCustomerId());
        Optional.ofNullable(request.getSecret())
                .ifPresent((value) -> builder.secret(value.orElse(null)));
        builder.isConfirmed(true);

        entity = publicCustomerVerifyPhoneRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        ProfilePublicCustomerVerifyPhoneModel updated = conversionService.convert(entity, ProfilePublicCustomerVerifyPhoneModel.class);

        return ProfilePublicCustomerVerifyPhoneResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public Page<ProfilePublicCustomerVerifyPhoneModel> select(
        ProfilePublicCustomerVerifyPhoneRequest.Context context,
        ProfilePublicCustomerVerifyPhoneRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicCustomerVerifyPhoneEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withValueMatch("customerId", context.getCustomerId())
                    .withOptionalMatch("phone", request.getPhone())
                    .withOptionalMatch("isConfirmed", request.getIsConfirmed())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicCustomerVerifyPhoneRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, ProfilePublicCustomerVerifyPhoneModel.class));
    }
    
    @Transactional
    public ProfilePublicCustomerVerifyPhoneModel get(
        ProfilePublicCustomerVerifyPhoneRequest.Context context,
        ProfilePublicCustomerVerifyPhoneRequest.Id key
    ) {
        PublicCustomerVerifyPhoneEntity entity = publicCustomerVerifyPhoneRepository
                .findById(key.getId())
                .orElseThrow();

        ProfilePublicCustomerVerifyPhoneModel result = conversionService.convert(entity, ProfilePublicCustomerVerifyPhoneModel.class);
        return result;
    }
}
