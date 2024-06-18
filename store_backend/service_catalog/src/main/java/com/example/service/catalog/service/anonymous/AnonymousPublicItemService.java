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
import com.example.service.catalog.repository.PublicItemRepository;
import com.example.service.catalog.model.anonymous.AnonymousPublicItemModel;
import com.example.service.catalog.request.anonymous.AnonymousPublicItemRequest;
import com.example.service.catalog.response.anonymous.AnonymousPublicItemResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnonymousPublicItemService {

    @Qualifier("serviceCatalog.publicItemRepository")
    private final PublicItemRepository publicItemRepository;
    private final EntityManager entityManager;
    private final ConversionService conversionService;
    private final EvaluatorService evaluatorService;
    private final GeneratorService generatorService;
    
    @Transactional
    public Page<AnonymousPublicItemModel> select(
        AnonymousPublicItemRequest.Context context,
        AnonymousPublicItemRequest.Select request,
        Pageable pageable
    ) {

        Specification<PublicItemEntity> specification = (root, query, cb) -> {
            PredicateFactory predicateFactory = new PredicateFactory(root, query, cb);
            Predicate[] predicates = new Predicate [] {
                    predicateFactory.fromOptional("id", request.getId()),
                    predicateFactory.fromOptional("productId", request.getProductId()),
                    predicateFactory.fromOptional("shopId", request.getShopId()),
                    predicateFactory.fromOptional("ean13", request.getEan13()),
                    predicateFactory.fromOptional("code128", request.getCode128()),
            };
            return cb.and(Arrays.stream(predicates).filter(i -> i != null).toArray(Predicate[]::new));
        };

        return publicItemRepository
                .findAll(specification, pageable)
                .map(item -> conversionService.convert(item, AnonymousPublicItemModel.class));
    }
    
    @Transactional
    public AnonymousPublicItemModel get(
        AnonymousPublicItemRequest.Context context,
        AnonymousPublicItemRequest.Id key
    ) {
        PublicItemEntity entity = publicItemRepository
                .findById(key.getId())
                .orElseThrow();

        AnonymousPublicItemModel result = conversionService.convert(entity, AnonymousPublicItemModel.class);
        return result;
    }
}
