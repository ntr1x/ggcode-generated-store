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
import com.example.service.catalog.repository.PublicProductRepository;
import com.example.service.catalog.model.anonymous.AnonymousPublicProductModel;
import com.example.service.catalog.request.anonymous.AnonymousPublicProductRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicProductResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnonymousPublicProductService {

    @Qualifier("serviceCatalog.publicProductRepository")
    private final PublicProductRepository publicProductRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public Page<AnonymousPublicProductModel> select(
        AnonymousPublicProductRequest.Context context,
        AnonymousPublicProductRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicProductEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withOptionalMatch("price", request.getPrice())
                    .withOptionalMatch("ean13", request.getEan13())
                    .withOptionalMatch("categoryId", request.getCategoryId())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

        return publicProductRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, AnonymousPublicProductModel.class));
    }
    
    @Transactional
    public AnonymousPublicProductModel get(
        AnonymousPublicProductRequest.Context context,
        AnonymousPublicProductRequest.Id key
    ) {
        PublicProductEntity entity = publicProductRepository
                .findById(key.getId())
                .orElseThrow();

        AnonymousPublicProductModel result = conversionService.convert(entity, AnonymousPublicProductModel.class);
        return result;
    }
}
