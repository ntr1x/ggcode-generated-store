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
import com.example.service.structure.repository.PublicNetworkRepository;
import com.example.service.structure.model.system.SystemPublicNetworkModel;
import com.example.service.structure.request.system.SystemPublicNetworkRequest;
import com.example.service.structure.response.system.SystemPublicNetworkResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicNetworkService {

    @Qualifier("serviceStructure.publicNetworkRepository")
    private final PublicNetworkRepository publicNetworkRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicNetworkResponse.Create create(
        SystemPublicNetworkRequest.Context context,
        SystemPublicNetworkRequest.Create request
    ) {

        PublicNetworkEntity.PublicNetworkEntityBuilder builder = PublicNetworkEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPassword())
                .ifPresent((value) -> builder.password(value.orElse(null)));
        Optional.ofNullable(request.getHidden())
                .ifPresent((value) -> builder.hidden(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        PublicNetworkEntity entity = publicNetworkRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicNetworkModel created = conversionService.convert(entity, SystemPublicNetworkModel.class);

        return SystemPublicNetworkResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicNetworkResponse.Remove remove(
        SystemPublicNetworkRequest.Context context,
        SystemPublicNetworkRequest.Id key
    ) {
        PublicNetworkEntity entity = publicNetworkRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicNetworkModel removed = conversionService.convert(entity, SystemPublicNetworkModel.class);

        publicNetworkRepository.delete(entity);

        return SystemPublicNetworkResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicNetworkResponse.RemoveAll removeAll(
        SystemPublicNetworkRequest.Context context,
        Collection<SystemPublicNetworkRequest.Id> keys
    ) {
        Collection<PublicNetworkEntity> entities = publicNetworkRepository
                .findAllById(keys.stream().map(SystemPublicNetworkRequest.Id::getId).toList());

        Collection<SystemPublicNetworkModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicNetworkModel.class))
                .toList();

        publicNetworkRepository.deleteAll(entities);

        return SystemPublicNetworkResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicNetworkResponse.Update update(
        SystemPublicNetworkRequest.Context context,
        SystemPublicNetworkRequest.Update request
    ) {

        Specification<PublicNetworkEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicNetworkEntity entity = publicNetworkRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicNetworkEntity.PublicNetworkEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPassword())
                .ifPresent((value) -> builder.password(value.orElse(null)));
        Optional.ofNullable(request.getHidden())
                .ifPresent((value) -> builder.hidden(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicNetworkRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicNetworkModel updated = conversionService.convert(entity, SystemPublicNetworkModel.class);

        return SystemPublicNetworkResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicNetworkResponse.Replace replace(
        SystemPublicNetworkRequest.Context context,
        SystemPublicNetworkRequest.Id key,
        SystemPublicNetworkRequest.Replace request
    ) {
        PublicNetworkEntity entity = publicNetworkRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicNetworkModel removed = conversionService.convert(entity, SystemPublicNetworkModel.class);

        PublicNetworkEntity.PublicNetworkEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPassword())
                .ifPresent((value) -> builder.password(value.orElse(null)));
        Optional.ofNullable(request.getHidden())
                .ifPresent((value) -> builder.hidden(value.orElse(null)));
        Optional.ofNullable(request.getShopId())
                .ifPresent((value) -> builder.shopId(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicNetworkRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicNetworkModel created = conversionService.convert(entity, SystemPublicNetworkModel.class);

        return SystemPublicNetworkResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicNetworkModel> select(
        SystemPublicNetworkRequest.Context context,
        SystemPublicNetworkRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicNetworkEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("password", request.getPassword())
                    .withOptionalMatch("hidden", request.getHidden())
                    .withOptionalMatch("shopId", request.getShopId())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("updatedAt", request.getUpdatedAt())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicNetworkRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicNetworkModel.class));
    }
    
    @Transactional
    public SystemPublicNetworkModel get(
        SystemPublicNetworkRequest.Context context,
        SystemPublicNetworkRequest.Id key
    ) {
        PublicNetworkEntity entity = publicNetworkRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicNetworkModel result = conversionService.convert(entity, SystemPublicNetworkModel.class);
        return result;
    }
}
