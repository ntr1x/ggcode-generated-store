package com.example.service.structure.service.system;

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

import com.example.service.structure.entity.*;
import com.example.service.structure.repository.PublicGuideRepository;
import com.example.service.structure.model.system.SystemPublicGuideModel;
import com.example.service.structure.request.system.SystemPublicGuideRequest;
import com.example.service.structure.response.system.SystemPublicGuideResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicGuideService {

    @Qualifier("serviceStructure.publicGuideRepository")
    private final PublicGuideRepository publicGuideRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicGuideResponse.Create create(
        SystemPublicGuideRequest.Context context,
        SystemPublicGuideRequest.Create request
    ) {

        PublicGuideEntity.PublicGuideEntityBuilder builder = PublicGuideEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getSubjectId())
                .ifPresent((value) -> builder.subjectId(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getLink())
                .ifPresent((value) -> builder.link(value.orElse(null)));
        Optional.ofNullable(request.getPosition())
                .ifPresent((value) -> builder.position(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        PublicGuideEntity entity = publicGuideRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicGuideModel created = conversionService.convert(entity, SystemPublicGuideModel.class);

        return SystemPublicGuideResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicGuideResponse.Remove remove(
        SystemPublicGuideRequest.Context context,
        SystemPublicGuideRequest.Id key
    ) {
        PublicGuideEntity entity = publicGuideRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicGuideModel removed = conversionService.convert(entity, SystemPublicGuideModel.class);

        publicGuideRepository.delete(entity);

        return SystemPublicGuideResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicGuideResponse.RemoveAll removeAll(
        SystemPublicGuideRequest.Context context,
        Collection<SystemPublicGuideRequest.Id> keys
    ) {
        Collection<PublicGuideEntity> entities = publicGuideRepository
                .findAllById(keys.stream().map(SystemPublicGuideRequest.Id::getId).toList());

        Collection<SystemPublicGuideModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicGuideModel.class))
                .toList();

        publicGuideRepository.deleteAll(entities);

        return SystemPublicGuideResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicGuideResponse.Update update(
        SystemPublicGuideRequest.Context context,
        SystemPublicGuideRequest.Update request
    ) {

        Specification<PublicGuideEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicGuideEntity entity = publicGuideRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicGuideEntity.PublicGuideEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getSubjectId())
                .ifPresent((value) -> builder.subjectId(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getLink())
                .ifPresent((value) -> builder.link(value.orElse(null)));
        Optional.ofNullable(request.getPosition())
                .ifPresent((value) -> builder.position(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicGuideRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicGuideModel updated = conversionService.convert(entity, SystemPublicGuideModel.class);

        return SystemPublicGuideResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicGuideResponse.Replace replace(
        SystemPublicGuideRequest.Context context,
        SystemPublicGuideRequest.Id key,
        SystemPublicGuideRequest.Replace request
    ) {
        PublicGuideEntity entity = publicGuideRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicGuideModel removed = conversionService.convert(entity, SystemPublicGuideModel.class);

        PublicGuideEntity.PublicGuideEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getSubjectId())
                .ifPresent((value) -> builder.subjectId(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getLink())
                .ifPresent((value) -> builder.link(value.orElse(null)));
        Optional.ofNullable(request.getPosition())
                .ifPresent((value) -> builder.position(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicGuideRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicGuideModel created = conversionService.convert(entity, SystemPublicGuideModel.class);

        return SystemPublicGuideResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicGuideModel> select(
        SystemPublicGuideRequest.Context context,
        SystemPublicGuideRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicGuideEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("subjectId", request.getSubjectId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("link", request.getLink())
                    .withOptionalMatch("position", request.getPosition())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("updatedAt", request.getUpdatedAt())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicGuideRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicGuideModel.class));
    }
    
    @Transactional
    public SystemPublicGuideModel get(
        SystemPublicGuideRequest.Context context,
        SystemPublicGuideRequest.Id key
    ) {
        PublicGuideEntity entity = publicGuideRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicGuideModel result = conversionService.convert(entity, SystemPublicGuideModel.class);
        return result;
    }
}
