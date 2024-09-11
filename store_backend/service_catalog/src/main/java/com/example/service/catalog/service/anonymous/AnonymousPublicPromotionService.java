package com.example.service.catalog.service.anonymous;

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
import com.example.service.catalog.repository.PublicPromotionRepository;
import com.example.service.catalog.model.anonymous.AnonymousPublicPromotionModel;
import com.example.service.catalog.request.anonymous.AnonymousPublicPromotionRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicPromotionResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnonymousPublicPromotionService {

    @Qualifier("serviceCatalog.publicPromotionRepository")
    private final PublicPromotionRepository publicPromotionRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public Page<AnonymousPublicPromotionModel> select(
        AnonymousPublicPromotionRequest.Context context,
        AnonymousPublicPromotionRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicPromotionEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withOptionalMatch("typeId", request.getTypeId())
                    .withOptionalMatch("config", request.getConfig())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicPromotionRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, AnonymousPublicPromotionModel.class));
    }
    
    @Transactional
    public AnonymousPublicPromotionModel get(
        AnonymousPublicPromotionRequest.Context context,
        AnonymousPublicPromotionRequest.Id key
    ) {
        PublicPromotionEntity entity = publicPromotionRepository
                .findById(key.getId())
                .orElseThrow();

        AnonymousPublicPromotionModel result = conversionService.convert(entity, AnonymousPublicPromotionModel.class);
        return result;
    }
}
