package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicPromotionTypeEntity;
import com.example.service.catalog.model.system.SystemPublicPromotionTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicPromotionTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicPromotionTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicPromotionTypeEntity, SystemPublicPromotionTypeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicPromotionTypeModel convert(PublicPromotionTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicPromotionTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
