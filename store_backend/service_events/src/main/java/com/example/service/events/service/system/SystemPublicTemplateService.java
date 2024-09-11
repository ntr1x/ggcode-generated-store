package com.example.service.events.service.system;

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
import com.example.service.events.repository.PublicTemplateRepository;
import com.example.service.events.model.system.SystemPublicTemplateModel;
import com.example.service.events.request.system.SystemPublicTemplateRequest;
import com.example.service.events.response.system.SystemPublicTemplateResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicTemplateService {

    @Qualifier("serviceEvents.publicTemplateRepository")
    private final PublicTemplateRepository publicTemplateRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicTemplateResponse.Create create(
        SystemPublicTemplateRequest.Context context,
        SystemPublicTemplateRequest.Create request
    ) {

        PublicTemplateEntity.PublicTemplateEntityBuilder builder = PublicTemplateEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getShapeId())
                .ifPresent((value) -> builder.shapeId(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        PublicTemplateEntity entity = publicTemplateRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateModel created = conversionService.convert(entity, SystemPublicTemplateModel.class);

        return SystemPublicTemplateResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateResponse.Remove remove(
        SystemPublicTemplateRequest.Context context,
        SystemPublicTemplateRequest.Id key
    ) {
        PublicTemplateEntity entity = publicTemplateRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateModel removed = conversionService.convert(entity, SystemPublicTemplateModel.class);

        publicTemplateRepository.delete(entity);

        return SystemPublicTemplateResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateResponse.RemoveAll removeAll(
        SystemPublicTemplateRequest.Context context,
        Collection<SystemPublicTemplateRequest.Id> keys
    ) {
        Collection<PublicTemplateEntity> entities = publicTemplateRepository
                .findAllById(keys.stream().map(SystemPublicTemplateRequest.Id::getId).toList());

        Collection<SystemPublicTemplateModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicTemplateModel.class))
                .toList();

        publicTemplateRepository.deleteAll(entities);

        return SystemPublicTemplateResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateResponse.Update update(
        SystemPublicTemplateRequest.Context context,
        SystemPublicTemplateRequest.Update request
    ) {

        Specification<PublicTemplateEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicTemplateEntity entity = publicTemplateRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicTemplateEntity.PublicTemplateEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getShapeId())
                .ifPresent((value) -> builder.shapeId(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicTemplateRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateModel updated = conversionService.convert(entity, SystemPublicTemplateModel.class);

        return SystemPublicTemplateResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateResponse.Replace replace(
        SystemPublicTemplateRequest.Context context,
        SystemPublicTemplateRequest.Id key,
        SystemPublicTemplateRequest.Replace request
    ) {
        PublicTemplateEntity entity = publicTemplateRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateModel removed = conversionService.convert(entity, SystemPublicTemplateModel.class);

        PublicTemplateEntity.PublicTemplateEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getTypeId())
                .ifPresent((value) -> builder.typeId(value.orElse(null)));
        Optional.ofNullable(request.getShapeId())
                .ifPresent((value) -> builder.shapeId(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPayload())
                .ifPresent((value) -> builder.payload(value.orElse(null)));

        entity = publicTemplateRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateModel created = conversionService.convert(entity, SystemPublicTemplateModel.class);

        return SystemPublicTemplateResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicTemplateModel> select(
        SystemPublicTemplateRequest.Context context,
        SystemPublicTemplateRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicTemplateEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("typeId", request.getTypeId())
                    .withOptionalMatch("shapeId", request.getShapeId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("payload", request.getPayload())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicTemplateRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicTemplateModel.class));
    }
    
    @Transactional
    public SystemPublicTemplateModel get(
        SystemPublicTemplateRequest.Context context,
        SystemPublicTemplateRequest.Id key
    ) {
        PublicTemplateEntity entity = publicTemplateRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateModel result = conversionService.convert(entity, SystemPublicTemplateModel.class);
        return result;
    }
}
