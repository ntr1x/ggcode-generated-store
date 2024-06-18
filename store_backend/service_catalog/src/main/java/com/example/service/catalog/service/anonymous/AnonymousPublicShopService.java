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
import com.example.service.catalog.repository.PublicShopRepository;
import com.example.service.catalog.model.anonymous.AnonymousPublicShopModel;
import com.example.service.catalog.request.anonymous.AnonymousPublicShopRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicShopResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnonymousPublicShopService {

    @Qualifier("serviceCatalog.publicShopRepository")
    private final PublicShopRepository publicShopRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public Page<AnonymousPublicShopModel> select(
        AnonymousPublicShopRequest.Context context,
        AnonymousPublicShopRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicShopEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("name", request.getName()),
                    predicateFactory.fromOptional("description", request.getDescription()),
                    predicateFactory.fromOptional("address", request.getAddress()),
                    predicateFactory.fromOptional("lon", request.getLon()),
                    predicateFactory.fromOptional("lat", request.getLat()),
                    predicateFactory.fromOptional("regionId", request.getRegionId()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicShopRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, AnonymousPublicShopModel.class));
    }
    
    @Transactional
    public AnonymousPublicShopModel get(
        AnonymousPublicShopRequest.Context context,
        AnonymousPublicShopRequest.Id key
    ) {
        PublicShopEntity entity = publicShopRepository
                .findById(key.getId())
                .orElseThrow();

        AnonymousPublicShopModel result = conversionService.convert(entity, AnonymousPublicShopModel.class);
        return result;
    }
}
