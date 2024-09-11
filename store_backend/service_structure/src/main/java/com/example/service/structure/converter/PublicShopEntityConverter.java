package com.example.service.structure.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.entity.PublicShopEntity;
import com.example.service.structure.model.PublicShopModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicShopEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceStructure.publicShopEntityConverter.toModel")
    class ToModel implements AppConverter<PublicShopEntity, PublicShopModel> {
        private final ApplicationContext applicationContext;

        public PublicShopModel convert(PublicShopEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicShopModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
