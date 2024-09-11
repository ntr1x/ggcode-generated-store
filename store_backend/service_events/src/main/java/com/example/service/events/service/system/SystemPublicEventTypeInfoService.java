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
import com.example.service.events.repository.PublicEventTypeInfoRepository;
import com.example.service.events.model.system.SystemPublicEventTypeInfoModel;
import com.example.service.events.request.system.SystemPublicEventTypeInfoRequest;
import com.example.service.events.response.system.SystemPublicEventTypeInfoResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicEventTypeInfoService {

    @Qualifier("serviceEvents.publicEventTypeInfoRepository")
    private final PublicEventTypeInfoRepository publicEventTypeInfoRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicEventTypeInfoResponse.Create create(
        SystemPublicEventTypeInfoRequest.Context context,
        SystemPublicEventTypeInfoRequest.Create request
    ) {

        PublicEventTypeInfoEntity.PublicEventTypeInfoEntityBuilder builder = PublicEventTypeInfoEntity.builder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicEventTypeInfoEntity entity = publicEventTypeInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventTypeInfoModel created = conversionService.convert(entity, SystemPublicEventTypeInfoModel.class);

        return SystemPublicEventTypeInfoResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicEventTypeInfoResponse.Remove remove(
        SystemPublicEventTypeInfoRequest.Context context,
        SystemPublicEventTypeInfoRequest.Id key
    ) {
        PublicEventTypeInfoEntity entity = publicEventTypeInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventTypeInfoModel removed = conversionService.convert(entity, SystemPublicEventTypeInfoModel.class);

        publicEventTypeInfoRepository.delete(entity);

        return SystemPublicEventTypeInfoResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicEventTypeInfoResponse.RemoveAll removeAll(
        SystemPublicEventTypeInfoRequest.Context context,
        Collection<SystemPublicEventTypeInfoRequest.Id> keys
    ) {
        Collection<PublicEventTypeInfoEntity> entities = publicEventTypeInfoRepository
                .findAllById(keys.stream().map(SystemPublicEventTypeInfoRequest.Id::getName).toList());

        Collection<SystemPublicEventTypeInfoModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicEventTypeInfoModel.class))
                .toList();

        publicEventTypeInfoRepository.deleteAll(entities);

        return SystemPublicEventTypeInfoResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicEventTypeInfoResponse.Update update(
        SystemPublicEventTypeInfoRequest.Context context,
        SystemPublicEventTypeInfoRequest.Update request
    ) {

        Specification<PublicEventTypeInfoEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("name", request.getName())
                    .build();

        PublicEventTypeInfoEntity entity = publicEventTypeInfoRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicEventTypeInfoEntity.PublicEventTypeInfoEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicEventTypeInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventTypeInfoModel updated = conversionService.convert(entity, SystemPublicEventTypeInfoModel.class);

        return SystemPublicEventTypeInfoResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicEventTypeInfoResponse.Replace replace(
        SystemPublicEventTypeInfoRequest.Context context,
        SystemPublicEventTypeInfoRequest.Id key,
        SystemPublicEventTypeInfoRequest.Replace request
    ) {
        PublicEventTypeInfoEntity entity = publicEventTypeInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventTypeInfoModel removed = conversionService.convert(entity, SystemPublicEventTypeInfoModel.class);

        PublicEventTypeInfoEntity.PublicEventTypeInfoEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicEventTypeInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventTypeInfoModel created = conversionService.convert(entity, SystemPublicEventTypeInfoModel.class);

        return SystemPublicEventTypeInfoResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicEventTypeInfoModel> select(
        SystemPublicEventTypeInfoRequest.Context context,
        SystemPublicEventTypeInfoRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicEventTypeInfoEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicEventTypeInfoRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicEventTypeInfoModel.class));
    }
    
    @Transactional
    public SystemPublicEventTypeInfoModel get(
        SystemPublicEventTypeInfoRequest.Context context,
        SystemPublicEventTypeInfoRequest.Id key
    ) {
        PublicEventTypeInfoEntity entity = publicEventTypeInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventTypeInfoModel result = conversionService.convert(entity, SystemPublicEventTypeInfoModel.class);
        return result;
    }
}
