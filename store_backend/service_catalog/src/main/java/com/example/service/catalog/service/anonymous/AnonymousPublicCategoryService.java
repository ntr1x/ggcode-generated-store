package com.example.service.catalog.service.anonymous;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.ntr1x.common.api.annotation.Event;
import org.ntr1x.common.api.service.EvaluatorService;
import org.ntr1x.common.api.service.GeneratorService;
import org.ntr1x.common.jpa.criteria.PredicateFactory;
import org.ntr1x.common.web.util.Validate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.service.catalog.entity.*;
import com.example.service.catalog.repository.PublicCategoryRepository;
import com.example.service.catalog.model.anonymous.AnonymousPublicCategoryModel;
import com.example.service.catalog.request.anonymous.AnonymousPublicCategoryRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicCategoryResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnonymousPublicCategoryService {

    @Qualifier("serviceCatalog.publicCategoryRepository")
    private final PublicCategoryRepository publicCategoryRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public Page<AnonymousPublicCategoryModel> select(
        AnonymousPublicCategoryRequest.Context context,
        AnonymousPublicCategoryRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicCategoryEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("name", request.getName()),
                    predicateFactory.fromOptional("description", request.getDescription()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicCategoryRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, AnonymousPublicCategoryModel.class));
    }
    
    @Transactional
    public AnonymousPublicCategoryModel get(
        AnonymousPublicCategoryRequest.Context context,
        AnonymousPublicCategoryRequest.Id key
    ) {
        PublicCategoryEntity entity = publicCategoryRepository
                .findById(key.getId())
                .orElseThrow();

        AnonymousPublicCategoryModel result = conversionService.convert(entity, AnonymousPublicCategoryModel.class);
        return result;
    }
}
