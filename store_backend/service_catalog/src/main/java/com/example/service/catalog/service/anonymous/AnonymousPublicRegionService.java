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
import com.example.service.catalog.repository.PublicRegionRepository;
import com.example.service.catalog.model.anonymous.AnonymousPublicRegionModel;
import com.example.service.catalog.request.anonymous.AnonymousPublicRegionRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicRegionResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnonymousPublicRegionService {

    @Qualifier("serviceCatalog.publicRegionRepository")
    private final PublicRegionRepository publicRegionRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public Page<AnonymousPublicRegionModel> select(
        AnonymousPublicRegionRequest.Context context,
        AnonymousPublicRegionRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicRegionEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("name", request.getName()),
                    predicateFactory.fromOptional("description", request.getDescription()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicRegionRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, AnonymousPublicRegionModel.class));
    }
    
    @Transactional
    public AnonymousPublicRegionModel get(
        AnonymousPublicRegionRequest.Context context,
        AnonymousPublicRegionRequest.Id key
    ) {
        PublicRegionEntity entity = publicRegionRepository
                .findById(key.getId())
                .orElseThrow();

        AnonymousPublicRegionModel result = conversionService.convert(entity, AnonymousPublicRegionModel.class);
        return result;
    }
}
