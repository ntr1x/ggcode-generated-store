package com.example.service.payments.service.system;

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
import com.example.service.payments.repository.PublicPaymentStatusRepository;
import com.example.service.payments.model.system.SystemPublicPaymentStatusModel;
import com.example.service.payments.request.system.SystemPublicPaymentStatusRequest;
import com.example.service.payments.response.system.SystemPublicPaymentStatusResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicPaymentStatusService {

    @Qualifier("servicePayments.publicPaymentStatusRepository")
    private final PublicPaymentStatusRepository publicPaymentStatusRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicPaymentStatusResponse.Create create(
        SystemPublicPaymentStatusRequest.Context context,
        SystemPublicPaymentStatusRequest.Create request
    ) {

        PublicPaymentStatusEntity.PublicPaymentStatusEntityBuilder builder = PublicPaymentStatusEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicPaymentStatusEntity entity = publicPaymentStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPaymentStatusModel created = conversionService.convert(entity, SystemPublicPaymentStatusModel.class);

        return SystemPublicPaymentStatusResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicPaymentStatusResponse.Remove remove(
        SystemPublicPaymentStatusRequest.Context context,
        SystemPublicPaymentStatusRequest.Id key
    ) {
        PublicPaymentStatusEntity entity = publicPaymentStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPaymentStatusModel removed = conversionService.convert(entity, SystemPublicPaymentStatusModel.class);

        publicPaymentStatusRepository.delete(entity);

        return SystemPublicPaymentStatusResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicPaymentStatusResponse.RemoveAll removeAll(
        SystemPublicPaymentStatusRequest.Context context,
        Collection<SystemPublicPaymentStatusRequest.Id> keys
    ) {
        Collection<PublicPaymentStatusEntity> entities = publicPaymentStatusRepository
                .findAllById(keys.stream().map(SystemPublicPaymentStatusRequest.Id::getId).toList());

        Collection<SystemPublicPaymentStatusModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicPaymentStatusModel.class))
                .toList();

        publicPaymentStatusRepository.deleteAll(entities);

        return SystemPublicPaymentStatusResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicPaymentStatusResponse.Update update(
        SystemPublicPaymentStatusRequest.Context context,
        SystemPublicPaymentStatusRequest.Update request
    ) {

        Specification<PublicPaymentStatusEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicPaymentStatusEntity entity = publicPaymentStatusRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicPaymentStatusEntity.PublicPaymentStatusEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicPaymentStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPaymentStatusModel updated = conversionService.convert(entity, SystemPublicPaymentStatusModel.class);

        return SystemPublicPaymentStatusResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicPaymentStatusResponse.Replace replace(
        SystemPublicPaymentStatusRequest.Context context,
        SystemPublicPaymentStatusRequest.Id key,
        SystemPublicPaymentStatusRequest.Replace request
    ) {
        PublicPaymentStatusEntity entity = publicPaymentStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPaymentStatusModel removed = conversionService.convert(entity, SystemPublicPaymentStatusModel.class);

        PublicPaymentStatusEntity.PublicPaymentStatusEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicPaymentStatusRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPaymentStatusModel created = conversionService.convert(entity, SystemPublicPaymentStatusModel.class);

        return SystemPublicPaymentStatusResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicPaymentStatusModel> select(
        SystemPublicPaymentStatusRequest.Context context,
        SystemPublicPaymentStatusRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicPaymentStatusEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicPaymentStatusRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicPaymentStatusModel.class));
    }
    
    @Transactional
    public SystemPublicPaymentStatusModel get(
        SystemPublicPaymentStatusRequest.Context context,
        SystemPublicPaymentStatusRequest.Id key
    ) {
        PublicPaymentStatusEntity entity = publicPaymentStatusRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPaymentStatusModel result = conversionService.convert(entity, SystemPublicPaymentStatusModel.class);
        return result;
    }
}
