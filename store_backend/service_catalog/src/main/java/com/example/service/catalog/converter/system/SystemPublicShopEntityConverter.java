package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicShopEntity;
import com.example.service.catalog.model.system.SystemPublicShopModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicShopEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicShopEntityConverter.toModel")
    class ToModel implements AppConverter<PublicShopEntity, SystemPublicShopModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicShopModel convert(PublicShopEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicShopModel.builder()
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
