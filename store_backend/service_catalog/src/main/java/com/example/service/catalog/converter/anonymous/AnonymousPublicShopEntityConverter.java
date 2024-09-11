package com.example.service.catalog.converter.anonymous;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicShopEntity;
import com.example.service.catalog.model.anonymous.AnonymousPublicShopModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface AnonymousPublicShopEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.anonymousPublicShopEntityConverter.toModel")
    class ToModel implements AppConverter<PublicShopEntity, AnonymousPublicShopModel> {
        private final ApplicationContext applicationContext;

        public AnonymousPublicShopModel convert(PublicShopEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return AnonymousPublicShopModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .address(source.getAddress())
                    .lon(source.getLon())
                    .lat(source.getLat())
                    .regionId(source.getRegionId())
                    .region(conversionService.convert(
                            source.getRegion(),
                            com.example.service.catalog.model.PublicRegionModel.class))
                    .build();
        }
    }
}
