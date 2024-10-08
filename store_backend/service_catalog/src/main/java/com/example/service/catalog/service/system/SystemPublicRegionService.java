package com.example.service.catalog.service.system;

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

import com.example.service.catalog.entity.*;
import com.example.service.catalog.repository.PublicRegionRepository;
import com.example.service.catalog.model.system.SystemPublicRegionModel;
import com.example.service.catalog.request.system.SystemPublicRegionRequest;
import com.example.service.catalog.response.system.SystemPublicRegionResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicRegionService {

    @Qualifier("serviceCatalog.publicRegionRepository")
    private final PublicRegionRepository publicRegionRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicRegionResponse.Create create(
        SystemPublicRegionRequest.Context context,
        SystemPublicRegionRequest.Create request
    ) {

        PublicRegionEntity.PublicRegionEntityBuilder builder = PublicRegionEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicRegionEntity entity = publicRegionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicRegionModel created = conversionService.convert(entity, SystemPublicRegionModel.class);

        return SystemPublicRegionResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicRegionResponse.Remove remove(
        SystemPublicRegionRequest.Context context,
        SystemPublicRegionRequest.Id key
    ) {
        PublicRegionEntity entity = publicRegionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicRegionModel removed = conversionService.convert(entity, SystemPublicRegionModel.class);

        publicRegionRepository.delete(entity);

        return SystemPublicRegionResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicRegionResponse.RemoveAll removeAll(
        SystemPublicRegionRequest.Context context,
        Collection<SystemPublicRegionRequest.Id> keys
    ) {
        Collection<PublicRegionEntity> entities = publicRegionRepository
                .findAllById(keys.stream().map(SystemPublicRegionRequest.Id::getId).toList());

        Collection<SystemPublicRegionModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicRegionModel.class))
                .toList();

        publicRegionRepository.deleteAll(entities);

        return SystemPublicRegionResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicRegionResponse.Update update(
        SystemPublicRegionRequest.Context context,
        SystemPublicRegionRequest.Update request
    ) {

        Specification<PublicRegionEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicRegionEntity entity = publicRegionRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicRegionEntity.PublicRegionEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicRegionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicRegionModel updated = conversionService.convert(entity, SystemPublicRegionModel.class);

        return SystemPublicRegionResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicRegionResponse.Replace replace(
        SystemPublicRegionRequest.Context context,
        SystemPublicRegionRequest.Id key,
        SystemPublicRegionRequest.Replace request
    ) {
        PublicRegionEntity entity = publicRegionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicRegionModel removed = conversionService.convert(entity, SystemPublicRegionModel.class);

        PublicRegionEntity.PublicRegionEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicRegionRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicRegionModel created = conversionService.convert(entity, SystemPublicRegionModel.class);

        return SystemPublicRegionResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicRegionModel> select(
        SystemPublicRegionRequest.Context context,
        SystemPublicRegionRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicRegionEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicRegionRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicRegionModel.class));
    }
    
    @Transactional
    public SystemPublicRegionModel get(
        SystemPublicRegionRequest.Context context,
        SystemPublicRegionRequest.Id key
    ) {
        PublicRegionEntity entity = publicRegionRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicRegionModel result = conversionService.convert(entity, SystemPublicRegionModel.class);
        return result;
    }
}
