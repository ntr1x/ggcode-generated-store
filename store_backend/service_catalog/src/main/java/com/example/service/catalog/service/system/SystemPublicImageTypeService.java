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
import com.example.service.catalog.repository.PublicImageTypeRepository;
import com.example.service.catalog.model.system.SystemPublicImageTypeModel;
import com.example.service.catalog.request.system.SystemPublicImageTypeRequest;
import com.example.service.catalog.response.system.SystemPublicImageTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicImageTypeService {

    @Qualifier("serviceCatalog.publicImageTypeRepository")
    private final PublicImageTypeRepository publicImageTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicImageTypeResponse.Create create(
        SystemPublicImageTypeRequest.Context context,
        SystemPublicImageTypeRequest.Create request
    ) {

        PublicImageTypeEntity.PublicImageTypeEntityBuilder builder = PublicImageTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getFormat())
                .ifPresent((value) -> builder.format(value.orElse(null)));
        Optional.ofNullable(request.getWidth())
                .ifPresent((value) -> builder.width(value.orElse(null)));
        Optional.ofNullable(request.getHeight())
                .ifPresent((value) -> builder.height(value.orElse(null)));

        PublicImageTypeEntity entity = publicImageTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicImageTypeModel created = conversionService.convert(entity, SystemPublicImageTypeModel.class);

        return SystemPublicImageTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicImageTypeResponse.Remove remove(
        SystemPublicImageTypeRequest.Context context,
        SystemPublicImageTypeRequest.Id key
    ) {
        PublicImageTypeEntity entity = publicImageTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicImageTypeModel removed = conversionService.convert(entity, SystemPublicImageTypeModel.class);

        publicImageTypeRepository.delete(entity);

        return SystemPublicImageTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicImageTypeResponse.Update update(
        SystemPublicImageTypeRequest.Context context,
        SystemPublicImageTypeRequest.Update request
    ) {

        Specification<PublicImageTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicImageTypeEntity entity = publicImageTypeRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicImageTypeEntity.PublicImageTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getFormat())
                .ifPresent((value) -> builder.format(value.orElse(null)));
        Optional.ofNullable(request.getWidth())
                .ifPresent((value) -> builder.width(value.orElse(null)));
        Optional.ofNullable(request.getHeight())
                .ifPresent((value) -> builder.height(value.orElse(null)));

        entity = publicImageTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicImageTypeModel updated = conversionService.convert(entity, SystemPublicImageTypeModel.class);

        return SystemPublicImageTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicImageTypeResponse.Replace replace(
        SystemPublicImageTypeRequest.Context context,
        SystemPublicImageTypeRequest.Id key,
        SystemPublicImageTypeRequest.Replace request
    ) {
        PublicImageTypeEntity entity = publicImageTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicImageTypeModel removed = conversionService.convert(entity, SystemPublicImageTypeModel.class);

        PublicImageTypeEntity.PublicImageTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getFormat())
                .ifPresent((value) -> builder.format(value.orElse(null)));
        Optional.ofNullable(request.getWidth())
                .ifPresent((value) -> builder.width(value.orElse(null)));
        Optional.ofNullable(request.getHeight())
                .ifPresent((value) -> builder.height(value.orElse(null)));

        entity = publicImageTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicImageTypeModel created = conversionService.convert(entity, SystemPublicImageTypeModel.class);

        return SystemPublicImageTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicImageTypeModel> select(
        SystemPublicImageTypeRequest.Context context,
        SystemPublicImageTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicImageTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withOptionalMatch("format", request.getFormat())
                    .withOptionalMatch("width", request.getWidth())
                    .withOptionalMatch("height", request.getHeight())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicImageTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicImageTypeModel.class));
    }
    
    @Transactional
    public SystemPublicImageTypeModel get(
        SystemPublicImageTypeRequest.Context context,
        SystemPublicImageTypeRequest.Id key
    ) {
        PublicImageTypeEntity entity = publicImageTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicImageTypeModel result = conversionService.convert(entity, SystemPublicImageTypeModel.class);
        return result;
    }
}
