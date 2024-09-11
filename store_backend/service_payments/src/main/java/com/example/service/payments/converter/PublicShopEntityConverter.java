package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicShopEntity;
import com.example.service.payments.model.PublicShopModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicShopEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicShopEntityConverter.toModel")
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
                            com.example.service.payments.model.PublicRegionModel.class))
                    .build();
        }
    }
}
