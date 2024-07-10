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

        Specification<PublicShopEntity> specification = (root, query, cb) ->
            SpecificationBuilder
                    .create(root, query, cb)
                    .withOptionalMatch("id", request.getId())
                    .withOptionalMatch("name", request.getName())
                    .withOptionalMatch("description", request.getDescription())
                    .withOptionalMatch("address", request.getAddress())
                    .withOptionalMatch("lon", request.getLon())
                    .withOptionalMatch("lat", request.getLat())
                    .withOptionalMatch("regionId", request.getRegionId())
                    .withWhereStatements(request.get__where())
                    .withOrderStatements(request.get__order())
                    .build();

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
