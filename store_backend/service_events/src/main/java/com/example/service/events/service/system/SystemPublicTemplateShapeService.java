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
import com.example.service.events.repository.PublicTemplateShapeRepository;
import com.example.service.events.model.system.SystemPublicTemplateShapeModel;
import com.example.service.events.request.system.SystemPublicTemplateShapeRequest;
import com.example.service.events.response.system.SystemPublicTemplateShapeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicTemplateShapeService {

    @Qualifier("serviceEvents.publicTemplateShapeRepository")
    private final PublicTemplateShapeRepository publicTemplateShapeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicTemplateShapeResponse.Create create(
        SystemPublicTemplateShapeRequest.Context context,
        SystemPublicTemplateShapeRequest.Create request
    ) {

        PublicTemplateShapeEntity.PublicTemplateShapeEntityBuilder builder = PublicTemplateShapeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicTemplateShapeEntity entity = publicTemplateShapeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateShapeModel created = conversionService.convert(entity, SystemPublicTemplateShapeModel.class);

        return SystemPublicTemplateShapeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateShapeResponse.Remove remove(
        SystemPublicTemplateShapeRequest.Context context,
        SystemPublicTemplateShapeRequest.Id key
    ) {
        PublicTemplateShapeEntity entity = publicTemplateShapeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateShapeModel removed = conversionService.convert(entity, SystemPublicTemplateShapeModel.class);

        publicTemplateShapeRepository.delete(entity);

        return SystemPublicTemplateShapeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateShapeResponse.RemoveAll removeAll(
        SystemPublicTemplateShapeRequest.Context context,
        Collection<SystemPublicTemplateShapeRequest.Id> keys
    ) {
        Collection<PublicTemplateShapeEntity> entities = publicTemplateShapeRepository
                .findAllById(keys.stream().map(SystemPublicTemplateShapeRequest.Id::getId).toList());

        Collection<SystemPublicTemplateShapeModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicTemplateShapeModel.class))
                .toList();

        publicTemplateShapeRepository.deleteAll(entities);

        return SystemPublicTemplateShapeResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateShapeResponse.Update update(
        SystemPublicTemplateShapeRequest.Context context,
        SystemPublicTemplateShapeRequest.Update request
    ) {

        Specification<PublicTemplateShapeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicTemplateShapeEntity entity = publicTemplateShapeRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicTemplateShapeEntity.PublicTemplateShapeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicTemplateShapeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateShapeModel updated = conversionService.convert(entity, SystemPublicTemplateShapeModel.class);

        return SystemPublicTemplateShapeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicTemplateShapeResponse.Replace replace(
        SystemPublicTemplateShapeRequest.Context context,
        SystemPublicTemplateShapeRequest.Id key,
        SystemPublicTemplateShapeRequest.Replace request
    ) {
        PublicTemplateShapeEntity entity = publicTemplateShapeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateShapeModel removed = conversionService.convert(entity, SystemPublicTemplateShapeModel.class);

        PublicTemplateShapeEntity.PublicTemplateShapeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicTemplateShapeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicTemplateShapeModel created = conversionService.convert(entity, SystemPublicTemplateShapeModel.class);

        return SystemPublicTemplateShapeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicTemplateShapeModel> select(
        SystemPublicTemplateShapeRequest.Context context,
        SystemPublicTemplateShapeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicTemplateShapeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicTemplateShapeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicTemplateShapeModel.class));
    }
    
    @Transactional
    public SystemPublicTemplateShapeModel get(
        SystemPublicTemplateShapeRequest.Context context,
        SystemPublicTemplateShapeRequest.Id key
    ) {
        PublicTemplateShapeEntity entity = publicTemplateShapeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicTemplateShapeModel result = conversionService.convert(entity, SystemPublicTemplateShapeModel.class);
        return result;
    }
}
