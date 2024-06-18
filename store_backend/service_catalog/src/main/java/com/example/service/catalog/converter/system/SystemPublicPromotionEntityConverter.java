package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicPromotionEntity;
import com.example.service.catalog.model.system.SystemPublicPromotionModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicPromotionEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicPromotionEntityConverter.toModel")
    class ToModel implements AppConverter<PublicPromotionEntity, SystemPublicPromotionModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicPromotionModel convert(PublicPromotionEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicPromotionModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .typeId(source.getTypeId())
                    .config(source.getConfig())
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.catalog.model.PublicPromotionTypeModel.class))
                    .build();
        }
    }
}
