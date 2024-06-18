package com.example.service.catalog.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicPromotionTypeEntity;
import com.example.service.catalog.model.PublicPromotionTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicPromotionTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.publicPromotionTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicPromotionTypeEntity, PublicPromotionTypeModel> {
        private final ApplicationContext applicationContext;

        public PublicPromotionTypeModel convert(PublicPromotionTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicPromotionTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
