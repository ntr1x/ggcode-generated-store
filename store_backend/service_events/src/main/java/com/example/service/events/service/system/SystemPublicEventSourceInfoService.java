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
import com.example.service.events.repository.PublicEventSourceInfoRepository;
import com.example.service.events.model.system.SystemPublicEventSourceInfoModel;
import com.example.service.events.request.system.SystemPublicEventSourceInfoRequest;
import com.example.service.events.response.system.SystemPublicEventSourceInfoResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicEventSourceInfoService {

    @Qualifier("serviceEvents.publicEventSourceInfoRepository")
    private final PublicEventSourceInfoRepository publicEventSourceInfoRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicEventSourceInfoResponse.Create create(
        SystemPublicEventSourceInfoRequest.Context context,
        SystemPublicEventSourceInfoRequest.Create request
    ) {

        PublicEventSourceInfoEntity.PublicEventSourceInfoEntityBuilder builder = PublicEventSourceInfoEntity.builder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicEventSourceInfoEntity entity = publicEventSourceInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventSourceInfoModel created = conversionService.convert(entity, SystemPublicEventSourceInfoModel.class);

        return SystemPublicEventSourceInfoResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicEventSourceInfoResponse.Remove remove(
        SystemPublicEventSourceInfoRequest.Context context,
        SystemPublicEventSourceInfoRequest.Id key
    ) {
        PublicEventSourceInfoEntity entity = publicEventSourceInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventSourceInfoModel removed = conversionService.convert(entity, SystemPublicEventSourceInfoModel.class);

        publicEventSourceInfoRepository.delete(entity);

        return SystemPublicEventSourceInfoResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicEventSourceInfoResponse.RemoveAll removeAll(
        SystemPublicEventSourceInfoRequest.Context context,
        Collection<SystemPublicEventSourceInfoRequest.Id> keys
    ) {
        Collection<PublicEventSourceInfoEntity> entities = publicEventSourceInfoRepository
                .findAllById(keys.stream().map(SystemPublicEventSourceInfoRequest.Id::getName).toList());

        Collection<SystemPublicEventSourceInfoModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicEventSourceInfoModel.class))
                .toList();

        publicEventSourceInfoRepository.deleteAll(entities);

        return SystemPublicEventSourceInfoResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicEventSourceInfoResponse.Update update(
        SystemPublicEventSourceInfoRequest.Context context,
        SystemPublicEventSourceInfoRequest.Update request
    ) {

        Specification<PublicEventSourceInfoEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("name", request.getName())
                    .build();

        PublicEventSourceInfoEntity entity = publicEventSourceInfoRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicEventSourceInfoEntity.PublicEventSourceInfoEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicEventSourceInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventSourceInfoModel updated = conversionService.convert(entity, SystemPublicEventSourceInfoModel.class);

        return SystemPublicEventSourceInfoResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicEventSourceInfoResponse.Replace replace(
        SystemPublicEventSourceInfoRequest.Context context,
        SystemPublicEventSourceInfoRequest.Id key,
        SystemPublicEventSourceInfoRequest.Replace request
    ) {
        PublicEventSourceInfoEntity entity = publicEventSourceInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventSourceInfoModel removed = conversionService.convert(entity, SystemPublicEventSourceInfoModel.class);

        PublicEventSourceInfoEntity.PublicEventSourceInfoEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicEventSourceInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventSourceInfoModel created = conversionService.convert(entity, SystemPublicEventSourceInfoModel.class);

        return SystemPublicEventSourceInfoResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicEventSourceInfoModel> select(
        SystemPublicEventSourceInfoRequest.Context context,
        SystemPublicEventSourceInfoRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicEventSourceInfoEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicEventSourceInfoRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicEventSourceInfoModel.class));
    }
    
    @Transactional
    public SystemPublicEventSourceInfoModel get(
        SystemPublicEventSourceInfoRequest.Context context,
        SystemPublicEventSourceInfoRequest.Id key
    ) {
        PublicEventSourceInfoEntity entity = publicEventSourceInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventSourceInfoModel result = conversionService.convert(entity, SystemPublicEventSourceInfoModel.class);
        return result;
    }
}
