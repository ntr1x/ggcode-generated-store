package com.example.service.profile.service.system;

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
import com.example.service.profile.repository.PublicAgentRepository;
import com.example.service.profile.model.system.SystemPublicAgentModel;
import com.example.service.profile.request.system.SystemPublicAgentRequest;
import com.example.service.profile.response.system.SystemPublicAgentResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicAgentService {

    @Qualifier("serviceProfile.publicAgentRepository")
    private final PublicAgentRepository publicAgentRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "public_agent", type = "created", source = "service:service_profile", payloadEl = "#result.created")
    public SystemPublicAgentResponse.Create create(
        SystemPublicAgentRequest.Context context,
        SystemPublicAgentRequest.Create request
    ) {

        PublicAgentEntity.PublicAgentEntityBuilder builder = PublicAgentEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getKind())
                .ifPresent((value) -> builder.kind(value.orElse(null)));
        Optional.ofNullable(request.getTitle())
                .ifPresent((value) -> builder.title(value.orElse(null)));
        Optional.ofNullable(request.getInn())
                .ifPresent((value) -> builder.inn(value.orElse(null)));
        Optional.ofNullable(request.getKpp())
                .ifPresent((value) -> builder.kpp(value.orElse(null)));
        Optional.ofNullable(request.getBic())
                .ifPresent((value) -> builder.bic(value.orElse(null)));
        Optional.ofNullable(request.getBank())
                .ifPresent((value) -> builder.bank(value.orElse(null)));
        Optional.ofNullable(request.getOkved())
                .ifPresent((value) -> builder.okved(value.orElse(null)));
        Optional.ofNullable(request.getAddress())
                .ifPresent((value) -> builder.address(value.orElse(null)));
        Optional.ofNullable(request.getPhone())
                .ifPresent((value) -> builder.phone(value.orElse(null)));
        Optional.ofNullable(request.getEmail())
                .ifPresent((value) -> builder.email(value.orElse(null)));
        Optional.ofNullable(request.getRegistrationAddress())
                .ifPresent((value) -> builder.registrationAddress(value.orElse(null)));

        PublicAgentEntity entity = publicAgentRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicAgentModel created = conversionService.convert(entity, SystemPublicAgentModel.class);

        return SystemPublicAgentResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_agent", type = "removed", source = "service:service_profile", payloadEl = "#result.removed")
    public SystemPublicAgentResponse.Remove remove(
        SystemPublicAgentRequest.Context context,
        SystemPublicAgentRequest.Id key
    ) {
        PublicAgentEntity entity = publicAgentRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicAgentModel removed = conversionService.convert(entity, SystemPublicAgentModel.class);

        publicAgentRepository.delete(entity);

        return SystemPublicAgentResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_agent", type = "removed", source = "service:service_profile", payloadEl = "#result.removed", unwind = true)
    public SystemPublicAgentResponse.RemoveAll removeAll(
        SystemPublicAgentRequest.Context context,
        Collection<SystemPublicAgentRequest.Id> keys
    ) {
        Collection<PublicAgentEntity> entities = publicAgentRepository
                .findAllById(keys.stream().map(SystemPublicAgentRequest.Id::getId).toList());

        Collection<SystemPublicAgentModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicAgentModel.class))
                .toList();

        publicAgentRepository.deleteAll(entities);

        return SystemPublicAgentResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_agent", type = "updated", source = "service:service_profile", payloadEl = "#result.updated")
    public SystemPublicAgentResponse.Update update(
        SystemPublicAgentRequest.Context context,
        SystemPublicAgentRequest.Update request
    ) {

        Specification<PublicAgentEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicAgentEntity entity = publicAgentRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicAgentEntity.PublicAgentEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getKind())
                .ifPresent((value) -> builder.kind(value.orElse(null)));
        Optional.ofNullable(request.getTitle())
                .ifPresent((value) -> builder.title(value.orElse(null)));
        Optional.ofNullable(request.getInn())
                .ifPresent((value) -> builder.inn(value.orElse(null)));
        Optional.ofNullable(request.getKpp())
                .ifPresent((value) -> builder.kpp(value.orElse(null)));
        Optional.ofNullable(request.getBic())
                .ifPresent((value) -> builder.bic(value.orElse(null)));
        Optional.ofNullable(request.getBank())
                .ifPresent((value) -> builder.bank(value.orElse(null)));
        Optional.ofNullable(request.getOkved())
                .ifPresent((value) -> builder.okved(value.orElse(null)));
        Optional.ofNullable(request.getAddress())
                .ifPresent((value) -> builder.address(value.orElse(null)));
        Optional.ofNullable(request.getPhone())
                .ifPresent((value) -> builder.phone(value.orElse(null)));
        Optional.ofNullable(request.getEmail())
                .ifPresent((value) -> builder.email(value.orElse(null)));
        Optional.ofNullable(request.getRegistrationAddress())
                .ifPresent((value) -> builder.registrationAddress(value.orElse(null)));

        entity = publicAgentRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicAgentModel updated = conversionService.convert(entity, SystemPublicAgentModel.class);

        return SystemPublicAgentResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_agent", type = "removed", source = "service:service_profile", payloadEl = "#result.removed")
    @Event(topic = "public_agent", type = "created", source = "service:service_profile", payloadEl = "#result.created")
    public SystemPublicAgentResponse.Replace replace(
        SystemPublicAgentRequest.Context context,
        SystemPublicAgentRequest.Id key,
        SystemPublicAgentRequest.Replace request
    ) {
        PublicAgentEntity entity = publicAgentRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicAgentModel removed = conversionService.convert(entity, SystemPublicAgentModel.class);

        PublicAgentEntity.PublicAgentEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getCustomerId())
                .ifPresent((value) -> builder.customerId(value.orElse(null)));
        Optional.ofNullable(request.getKind())
                .ifPresent((value) -> builder.kind(value.orElse(null)));
        Optional.ofNullable(request.getTitle())
                .ifPresent((value) -> builder.title(value.orElse(null)));
        Optional.ofNullable(request.getInn())
                .ifPresent((value) -> builder.inn(value.orElse(null)));
        Optional.ofNullable(request.getKpp())
                .ifPresent((value) -> builder.kpp(value.orElse(null)));
        Optional.ofNullable(request.getBic())
                .ifPresent((value) -> builder.bic(value.orElse(null)));
        Optional.ofNullable(request.getBank())
                .ifPresent((value) -> builder.bank(value.orElse(null)));
        Optional.ofNullable(request.getOkved())
                .ifPresent((value) -> builder.okved(value.orElse(null)));
        Optional.ofNullable(request.getAddress())
                .ifPresent((value) -> builder.address(value.orElse(null)));
        Optional.ofNullable(request.getPhone())
                .ifPresent((value) -> builder.phone(value.orElse(null)));
        Optional.ofNullable(request.getEmail())
                .ifPresent((value) -> builder.email(value.orElse(null)));
        Optional.ofNullable(request.getRegistrationAddress())
                .ifPresent((value) -> builder.registrationAddress(value.orElse(null)));

        entity = publicAgentRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicAgentModel created = conversionService.convert(entity, SystemPublicAgentModel.class);

        return SystemPublicAgentResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicAgentModel> select(
        SystemPublicAgentRequest.Context context,
        SystemPublicAgentRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicAgentEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("customerId", request.getCustomerId())
                    .withOptionalMatch("kind", request.getKind())
                    .withOptionalMatch("title", request.getTitle())
                    .withOptionalMatch("inn", request.getInn())
                    .withOptionalMatch("kpp", request.getKpp())
                    .withOptionalMatch("bic", request.getBic())
                    .withOptionalMatch("bank", request.getBank())
                    .withOptionalMatch("okved", request.getOkved())
                    .withOptionalMatch("address", request.getAddress())
                    .withOptionalMatch("phone", request.getPhone())
                    .withOptionalMatch("email", request.getEmail())
                    .withOptionalMatch("registrationAddress", request.getRegistrationAddress())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicAgentRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicAgentModel.class));
    }
    
    @Transactional
    public SystemPublicAgentModel get(
        SystemPublicAgentRequest.Context context,
        SystemPublicAgentRequest.Id key
    ) {
        PublicAgentEntity entity = publicAgentRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicAgentModel result = conversionService.convert(entity, SystemPublicAgentModel.class);
        return result;
    }
}
