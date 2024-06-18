package com.example.service.catalog.converter.anonymous;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicPromotionEntity;
import com.example.service.catalog.model.anonymous.AnonymousPublicPromotionModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface AnonymousPublicPromotionEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.anonymousPublicPromotionEntityConverter.toModel")
    class ToModel implements AppConverter<PublicPromotionEntity, AnonymousPublicPromotionModel> {
        private final ApplicationContext applicationContext;

        public AnonymousPublicPromotionModel convert(PublicPromotionEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return AnonymousPublicPromotionModel.builder()
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
