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
import com.example.service.events.repository.PublicEventTopicInfoRepository;
import com.example.service.events.model.system.SystemPublicEventTopicInfoModel;
import com.example.service.events.request.system.SystemPublicEventTopicInfoRequest;
import com.example.service.events.response.system.SystemPublicEventTopicInfoResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicEventTopicInfoService {

    @Qualifier("serviceEvents.publicEventTopicInfoRepository")
    private final PublicEventTopicInfoRepository publicEventTopicInfoRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicEventTopicInfoResponse.Create create(
        SystemPublicEventTopicInfoRequest.Context context,
        SystemPublicEventTopicInfoRequest.Create request
    ) {

        PublicEventTopicInfoEntity.PublicEventTopicInfoEntityBuilder builder = PublicEventTopicInfoEntity.builder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        PublicEventTopicInfoEntity entity = publicEventTopicInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventTopicInfoModel created = conversionService.convert(entity, SystemPublicEventTopicInfoModel.class);

        return SystemPublicEventTopicInfoResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicEventTopicInfoResponse.Remove remove(
        SystemPublicEventTopicInfoRequest.Context context,
        SystemPublicEventTopicInfoRequest.Id key
    ) {
        PublicEventTopicInfoEntity entity = publicEventTopicInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventTopicInfoModel removed = conversionService.convert(entity, SystemPublicEventTopicInfoModel.class);

        publicEventTopicInfoRepository.delete(entity);

        return SystemPublicEventTopicInfoResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicEventTopicInfoResponse.Update update(
        SystemPublicEventTopicInfoRequest.Context context,
        SystemPublicEventTopicInfoRequest.Update request
    ) {

        Specification<PublicEventTopicInfoEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("name", request.getName())
                    .build();

        PublicEventTopicInfoEntity entity = publicEventTopicInfoRepository
                .findOne(specification)
                .orElseThrow(() -> {
                        throw Validate.create(400, "Entity does not exist").buildError();
                });

        PublicEventTopicInfoEntity.PublicEventTopicInfoEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicEventTopicInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventTopicInfoModel updated = conversionService.convert(entity, SystemPublicEventTopicInfoModel.class);

        return SystemPublicEventTopicInfoResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicEventTopicInfoResponse.Replace replace(
        SystemPublicEventTopicInfoRequest.Context context,
        SystemPublicEventTopicInfoRequest.Id key,
        SystemPublicEventTopicInfoRequest.Replace request
    ) {
        PublicEventTopicInfoEntity entity = publicEventTopicInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventTopicInfoModel removed = conversionService.convert(entity, SystemPublicEventTopicInfoModel.class);

        PublicEventTopicInfoEntity.PublicEventTopicInfoEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getDescription())
                .ifPresent((value) -> builder.description(value.orElse(null)));

        entity = publicEventTopicInfoRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicEventTopicInfoModel created = conversionService.convert(entity, SystemPublicEventTopicInfoModel.class);

        return SystemPublicEventTopicInfoResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicEventTopicInfoModel> select(
        SystemPublicEventTopicInfoRequest.Context context,
        SystemPublicEventTopicInfoRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicEventTopicInfoEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicEventTopicInfoRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicEventTopicInfoModel.class));
    }
    
    @Transactional
    public SystemPublicEventTopicInfoModel get(
        SystemPublicEventTopicInfoRequest.Context context,
        SystemPublicEventTopicInfoRequest.Id key
    ) {
        PublicEventTopicInfoEntity entity = publicEventTopicInfoRepository
                .findById(key.getName())
                .orElseThrow();

        SystemPublicEventTopicInfoModel result = conversionService.convert(entity, SystemPublicEventTopicInfoModel.class);
        return result;
    }
}
