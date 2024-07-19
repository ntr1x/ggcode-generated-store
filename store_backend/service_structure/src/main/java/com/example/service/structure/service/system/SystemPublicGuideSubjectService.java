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
import com.example.service.structure.repository.PublicGuideSubjectRepository;
import com.example.service.structure.model.system.SystemPublicGuideSubjectModel;
import com.example.service.structure.request.system.SystemPublicGuideSubjectRequest;
import com.example.service.structure.response.system.SystemPublicGuideSubjectResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemPublicGuideSubjectService {

    @Qualifier("serviceStructure.publicGuideSubjectRepository")
    private final PublicGuideSubjectRepository publicGuideSubjectRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public SystemPublicGuideSubjectResponse.Create create(
        SystemPublicGuideSubjectRequest.Context context,
        SystemPublicGuideSubjectRequest.Create request
    ) {

        PublicGuideSubjectEntity.PublicGuideSubjectEntityBuilder builder = PublicGuideSubjectEntity.builder();
        
        builder.id(generatorService.randomUUID());
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPosition())
                .ifPresent((value) -> builder.position(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        PublicGuideSubjectEntity entity = publicGuideSubjectRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicGuideSubjectModel created = conversionService.convert(entity, SystemPublicGuideSubjectModel.class);

        return SystemPublicGuideSubjectResponse.Create
            .builder()
            .created(created)
            .build();
    }
    
    @Transactional
    public SystemPublicGuideSubjectResponse.Remove remove(
        SystemPublicGuideSubjectRequest.Context context,
        SystemPublicGuideSubjectRequest.Id key
    ) {
        PublicGuideSubjectEntity entity = publicGuideSubjectRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicGuideSubjectModel removed = conversionService.convert(entity, SystemPublicGuideSubjectModel.class);

        publicGuideSubjectRepository.delete(entity);

        return SystemPublicGuideSubjectResponse.Remove
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicGuideSubjectResponse.RemoveAll removeAll(
        SystemPublicGuideSubjectRequest.Context context,
        Collection<SystemPublicGuideSubjectRequest.Id> keys
    ) {
        Collection<PublicGuideSubjectEntity> entities = publicGuideSubjectRepository
                .findAllById(keys.stream().map(SystemPublicGuideSubjectRequest.Id::getId).toList());

        Collection<SystemPublicGuideSubjectModel> removed = entities
                .stream()
                .map(entity -> conversionService.convert(entity, SystemPublicGuideSubjectModel.class))
                .toList();

        publicGuideSubjectRepository.deleteAll(entities);

        return SystemPublicGuideSubjectResponse.RemoveAll
            .builder()
            .removed(removed)
            .build();
    }
    
    @Transactional
    public SystemPublicGuideSubjectResponse.Update update(
        SystemPublicGuideSubjectRequest.Context context,
        SystemPublicGuideSubjectRequest.Update request
    ) {

        Specification<PublicGuideSubjectEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withValueMatch("id", request.getId())
                    .build();

        PublicGuideSubjectEntity entity = publicGuideSubjectRepository
                .findOne(specification)
                .orElseThrow(() -> Validate.create(400, "Entity does not exist").buildError());

        PublicGuideSubjectEntity.PublicGuideSubjectEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPosition())
                .ifPresent((value) -> builder.position(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicGuideSubjectRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicGuideSubjectModel updated = conversionService.convert(entity, SystemPublicGuideSubjectModel.class);

        return SystemPublicGuideSubjectResponse.Update
            .builder()
            .updated(updated)
            .build();
    }
    
    @Transactional
    public SystemPublicGuideSubjectResponse.Replace replace(
        SystemPublicGuideSubjectRequest.Context context,
        SystemPublicGuideSubjectRequest.Id key,
        SystemPublicGuideSubjectRequest.Replace request
    ) {
        PublicGuideSubjectEntity entity = publicGuideSubjectRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicGuideSubjectModel removed = conversionService.convert(entity, SystemPublicGuideSubjectModel.class);

        PublicGuideSubjectEntity.PublicGuideSubjectEntityBuilder builder = entity.toBuilder();
        
        Optional.ofNullable(request.getId())
                .ifPresent((value) -> builder.id(value.orElse(null)));
        Optional.ofNullable(request.getName())
                .ifPresent((value) -> builder.name(value.orElse(null)));
        Optional.ofNullable(request.getPosition())
                .ifPresent((value) -> builder.position(value.orElse(null)));
        Optional.ofNullable(request.getCreatedAt())
                .ifPresent((value) -> builder.createdAt(value.orElse(null)));
        Optional.ofNullable(request.getUpdatedAt())
                .ifPresent((value) -> builder.updatedAt(value.orElse(null)));

        entity = publicGuideSubjectRepository.saveAndFlush(builder.build());

        entityManager.refresh(entity);

        SystemPublicGuideSubjectModel created = conversionService.convert(entity, SystemPublicGuideSubjectModel.class);

        return SystemPublicGuideSubjectResponse.Replace
            .builder()
            .removed(removed)
            .created(created)
            .build();
    }
    
    @Transactional
    public Page<SystemPublicGuideSubjectModel> select(
        SystemPublicGuideSubjectRequest.Context context,
        SystemPublicGuideSubjectRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicGuideSubjectEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("position", request.getPosition())
                    .withOptionalMatch("createdAt", request.getCreatedAt())
                    .withOptionalMatch("updatedAt", request.getUpdatedAt())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicGuideSubjectRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, SystemPublicGuideSubjectModel.class));
    }
    
    @Transactional
    public SystemPublicGuideSubjectModel get(
        SystemPublicGuideSubjectRequest.Context context,
        SystemPublicGuideSubjectRequest.Id key
    ) {
        PublicGuideSubjectEntity entity = publicGuideSubjectRepository
                .findById(key.getId())
                .orElseThrow();

        SystemPublicGuideSubjectModel result = conversionService.convert(entity, SystemPublicGuideSubjectModel.class);
        return result;
    }
}
