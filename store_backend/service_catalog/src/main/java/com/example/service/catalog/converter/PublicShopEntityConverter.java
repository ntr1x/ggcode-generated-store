package com.example.service.catalog.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicShopEntity;
import com.example.service.catalog.model.PublicShopModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicShopEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.publicShopEntityConverter.toModel")
    class ToModel implements AppConverter<PublicShopEntity, PublicShopModel> {
        private final ApplicationContext applicationContext;

        public PublicShopModel convert(PublicShopEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicShopModel.builder()
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
