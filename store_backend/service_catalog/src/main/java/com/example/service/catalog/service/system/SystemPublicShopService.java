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
import com.example.service.catalog.repository.PublicShopRepository;
import com.example.service.catalog.model.system.SystemPublicShopModel;
import com.example.service.catalog.request.system.SystemPublicShopRequest;
import com.example.service.catalog.response.system.SystemPublicShopResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicShopService {

    @Qualifier("serviceCatalog.publicShopRepository")
    private final PublicShopRepository publicShopRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicShopResponse.Create create(
        SystemPublicShopRequest.Context context,
        SystemPublicShopRequest.Create request
    ) {

        PublicShopEntity.PublicShopEntityBuilder builder = PublicShopEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getAddress())
                .ifPresent((value) -> builder.address(value.orElse(null)));
        Optional.ofNullable(request.getLon())
                .ifPresent((value) -> builder.lon(value.orElse(null)));
        Optional.ofNullable(request.getLat())
                .ifPresent((value) -> builder.lat(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));

        PublicShopEntity entity = publicShopRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicShopModel created = conversionService.convert(entity, SystemPublicShopModel.class);

        return SystemPublicShopResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicShopResponse.Remove remove(
        SystemPublicShopRequest.Context context,
        SystemPublicShopRequest.Id key
    ) {
        PublicShopEntity entity = publicShopRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicShopModel removed = conversionService.convert(entity, SystemPublicShopModel.class);

        publicShopRepository.delete(entity);

        return SystemPublicShopResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicShopResponse.RemoveAll removeAll(
        SystemPublicShopRequest.Context context,
        Collection<SystemPublicShopRequest.Id> keys
    ) {
        Collection<PublicShopEntity> entities = publicShopRepository
                .findAllById(keys.stream().map(SystemPublicShopRequest.Id::getId).toList());

        Collection<SystemPublicShopModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicShopModel.class))
                .toList();

        publicShopRepository.deleteAll(entities);

        return SystemPublicShopResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicShopResponse.Update update(
        SystemPublicShopRequest.Context context,
        SystemPublicShopRequest.Update request
    ) {

        Specification<PublicShopEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicShopEntity entity = publicShopRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicShopEntity.PublicShopEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getAddress())
                .ifPresent((value) -> builder.address(value.orElse(null)));
        Optional.ofNullable(request.getLon())
                .ifPresent((value) -> builder.lon(value.orElse(null)));
        Optional.ofNullable(request.getLat())
                .ifPresent((value) -> builder.lat(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));

        entity = publicShopRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicShopModel updated = conversionService.convert(entity, SystemPublicShopModel.class);

        return SystemPublicShopResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicShopResponse.Replace replace(
        SystemPublicShopRequest.Context context,
        SystemPublicShopRequest.Id key,
        SystemPublicShopRequest.Replace request
    ) {
        PublicShopEntity entity = publicShopRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicShopModel removed = conversionService.convert(entity, SystemPublicShopModel.class);

        PublicShopEntity.PublicShopEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));
        Optional.ofNullable(request.getAddress())
                .ifPresent((value) -> builder.address(value.orElse(null)));
        Optional.ofNullable(request.getLon())
                .ifPresent((value) -> builder.lon(value.orElse(null)));
        Optional.ofNullable(request.getLat())
                .ifPresent((value) -> builder.lat(value.orElse(null)));
        Optional.ofNullable(request.getRegionId())
                .ifPresent((value) -> builder.regionId(value.orElse(null)));

        entity = publicShopRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicShopModel created = conversionService.convert(entity, SystemPublicShopModel.class);

        return SystemPublicShopResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicShopModel> select(
        SystemPublicShopRequest.Context context,
        SystemPublicShopRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicShopEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withOptionalMatch("address", request.getAddress())
                    .withOptionalMatch("lon", request.getLon())
                    .withOptionalMatch("lat", request.getLat())
                    .withOptionalMatch("regionId", request.getRegionId())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicShopRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicShopModel.class));
    }
    
    @Transactional
    public SystemPublicShopModel get(
        SystemPublicShopRequest.Context context,
        SystemPublicShopRequest.Id key
    ) {
        PublicShopEntity entity = publicShopRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicShopModel result = conversionService.convert(entity, SystemPublicShopModel.class);
        return result;
    }
}
