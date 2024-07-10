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
import com.example.service.catalog.repository.PublicPromotionTypeRepository;
import com.example.service.catalog.model.system.SystemPublicPromotionTypeModel;
import com.example.service.catalog.request.system.SystemPublicPromotionTypeRequest;
import com.example.service.catalog.response.system.SystemPublicPromotionTypeResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicPromotionTypeService {

    @Qualifier("serviceCatalog.publicPromotionTypeRepository")
    private final PublicPromotionTypeRepository publicPromotionTypeRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicPromotionTypeResponse.Create create(
        SystemPublicPromotionTypeRequest.Context context,
        SystemPublicPromotionTypeRequest.Create request
    ) {

        PublicPromotionTypeEntity.PublicPromotionTypeEntityBuilder builder = PublicPromotionTypeEntity.builder();
        
        builder.id(null);
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicPromotionTypeEntity entity = publicPromotionTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionTypeModel created = conversionService.convert(entity, SystemPublicPromotionTypeModel.class);

        return SystemPublicPromotionTypeResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionTypeResponse.Remove remove(
        SystemPublicPromotionTypeRequest.Context context,
        SystemPublicPromotionTypeRequest.Id key
    ) {
        PublicPromotionTypeEntity entity = publicPromotionTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionTypeModel removed = conversionService.convert(entity, SystemPublicPromotionTypeModel.class);

        publicPromotionTypeRepository.delete(entity);

        return SystemPublicPromotionTypeResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionTypeResponse.Update update(
        SystemPublicPromotionTypeRequest.Context context,
        SystemPublicPromotionTypeRequest.Update request
    ) {

        Specification<PublicPromotionTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicPromotionTypeEntity entity = publicPromotionTypeRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicPromotionTypeEntity.PublicPromotionTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicPromotionTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionTypeModel updated = conversionService.convert(entity, SystemPublicPromotionTypeModel.class);

        return SystemPublicPromotionTypeResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicPromotionTypeResponse.Replace replace(
        SystemPublicPromotionTypeRequest.Context context,
        SystemPublicPromotionTypeRequest.Id key,
        SystemPublicPromotionTypeRequest.Replace request
    ) {
        PublicPromotionTypeEntity entity = publicPromotionTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionTypeModel removed = conversionService.convert(entity, SystemPublicPromotionTypeModel.class);

        PublicPromotionTypeEntity.PublicPromotionTypeEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicPromotionTypeRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicPromotionTypeModel created = conversionService.convert(entity, SystemPublicPromotionTypeModel.class);

        return SystemPublicPromotionTypeResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicPromotionTypeModel> select(
        SystemPublicPromotionTypeRequest.Context context,
        SystemPublicPromotionTypeRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicPromotionTypeEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicPromotionTypeRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicPromotionTypeModel.class));
    }
    
    @Transactional
    public SystemPublicPromotionTypeModel get(
        SystemPublicPromotionTypeRequest.Context context,
        SystemPublicPromotionTypeRequest.Id key
    ) {
        PublicPromotionTypeEntity entity = publicPromotionTypeRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicPromotionTypeModel result = conversionService.convert(entity, SystemPublicPromotionTypeModel.class);
        return result;
    }
}
