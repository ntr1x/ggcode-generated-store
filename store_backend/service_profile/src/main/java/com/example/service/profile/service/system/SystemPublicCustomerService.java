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
import com.example.service.profile.repository.PublicCustomerRepository;
import com.example.service.profile.model.system.SystemPublicCustomerModel;
import com.example.service.profile.request.system.SystemPublicCustomerRequest;
import com.example.service.profile.response.system.SystemPublicCustomerResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicCustomerService {

    @Qualifier("serviceProfile.publicCustomerRepository")
    private final PublicCustomerRepository publicCustomerRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    @Event(topic = "public_customer", type = "created", source = "service:service_profile", payloadEl = "#result.created")
    public SystemPublicCustomerResponse.Create create(
        SystemPublicCustomerRequest.Context context,
        SystemPublicCustomerRequest.Create request
    ) {

        PublicCustomerEntity.PublicCustomerEntityBuilder builder = PublicCustomerEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getEmail())
                .ifPresent((value) -> builder.email(value.orElse(null)));
        Optional.ofNullable(request.getPhone())
                .ifPresent((value) -> builder.phone(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getSurname())
                .ifPresent((value) -> builder.surname(value.orElse(null)));
        Optional.ofNullable(request.getPatronymic())
                .ifPresent((value) -> builder.patronymic(value.orElse(null)));

        PublicCustomerEntity entity = publicCustomerRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicCustomerModel created = conversionService.convert(entity, SystemPublicCustomerModel.class);

        return SystemPublicCustomerResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_customer", type = "removed", source = "service:service_profile", payloadEl = "#result.removed")
    public SystemPublicCustomerResponse.Remove remove(
        SystemPublicCustomerRequest.Context context,
        SystemPublicCustomerRequest.Id key
    ) {
        PublicCustomerEntity entity = publicCustomerRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicCustomerModel removed = conversionService.convert(entity, SystemPublicCustomerModel.class);

        publicCustomerRepository.delete(entity);

        return SystemPublicCustomerResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_customer", type = "removed", source = "service:service_profile", payloadEl = "#result.removed", unwind = true)
    public SystemPublicCustomerResponse.RemoveAll removeAll(
        SystemPublicCustomerRequest.Context context,
        Collection<SystemPublicCustomerRequest.Id> keys
    ) {
        Collection<PublicCustomerEntity> entities = publicCustomerRepository
                .findAllById(keys.stream().map(SystemPublicCustomerRequest.Id::getId).toList());

        Collection<SystemPublicCustomerModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicCustomerModel.class))
                .toList();

        publicCustomerRepository.deleteAll(entities);

        return SystemPublicCustomerResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_customer", type = "updated", source = "service:service_profile", payloadEl = "#result.updated")
    public SystemPublicCustomerResponse.Update update(
        SystemPublicCustomerRequest.Context context,
        SystemPublicCustomerRequest.Update request
    ) {

        Specification<PublicCustomerEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicCustomerEntity entity = publicCustomerRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicCustomerEntity.PublicCustomerEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getEmail())
                .ifPresent((value) -> builder.email(value.orElse(null)));
        Optional.ofNullable(request.getPhone())
                .ifPresent((value) -> builder.phone(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getSurname())
                .ifPresent((value) -> builder.surname(value.orElse(null)));
        Optional.ofNullable(request.getPatronymic())
                .ifPresent((value) -> builder.patronymic(value.orElse(null)));

        entity = publicCustomerRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicCustomerModel updated = conversionService.convert(entity, SystemPublicCustomerModel.class);

        return SystemPublicCustomerResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    @Event(topic = "public_customer", type = "removed", source = "service:service_profile", payloadEl = "#result.removed")
    @Event(topic = "public_customer", type = "created", source = "service:service_profile", payloadEl = "#result.created")
    public SystemPublicCustomerResponse.Replace replace(
        SystemPublicCustomerRequest.Context context,
        SystemPublicCustomerRequest.Id key,
        SystemPublicCustomerRequest.Replace request
    ) {
        PublicCustomerEntity entity = publicCustomerRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicCustomerModel removed = conversionService.convert(entity, SystemPublicCustomerModel.class);

        PublicCustomerEntity.PublicCustomerEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getEmail())
                .ifPresent((value) -> builder.email(value.orElse(null)));
        Optional.ofNullable(request.getPhone())
                .ifPresent((value) -> builder.phone(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getSurname())
                .ifPresent((value) -> builder.surname(value.orElse(null)));
        Optional.ofNullable(request.getPatronymic())
                .ifPresent((value) -> builder.patronymic(value.orElse(null)));

        entity = publicCustomerRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicCustomerModel created = conversionService.convert(entity, SystemPublicCustomerModel.class);

        return SystemPublicCustomerResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicCustomerModel> select(
        SystemPublicCustomerRequest.Context context,
        SystemPublicCustomerRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicCustomerEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("email", request.getEmail())
                    .withOptionalMatch("phone", request.getPhone())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("surname", request.getSurname())
                    .withOptionalMatch("patronymic", request.getPatronymic())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicCustomerRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicCustomerModel.class));
    }
    
    @Transactional
    public SystemPublicCustomerModel get(
        SystemPublicCustomerRequest.Context context,
        SystemPublicCustomerRequest.Id key
    ) {
        PublicCustomerEntity entity = publicCustomerRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicCustomerModel result = conversionService.convert(entity, SystemPublicCustomerModel.class);
        return result;
    }
}
