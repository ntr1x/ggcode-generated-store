package com.example.service.catalog.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicPromotionImageEntity;
import com.example.service.catalog.model.PublicPromotionImageModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicPromotionImageEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.publicPromotionImageEntityConverter.toModel")
    class ToModel implements AppConverter<PublicPromotionImageEntity, PublicPromotionImageModel> {
        private final ApplicationContext applicationContext;

        public PublicPromotionImageModel convert(PublicPromotionImageEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicPromotionImageModel.builder()
                    .id(source.getId())
                    .uri(source.getUri())
                    .name(source.getName())
                    .typeId(source.getTypeId())
                    .promotionId(source.getPromotionId())
                    .originalId(source.getOriginalId())
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.catalog.model.PublicImageTypeModel.class))
                    .promotion(conversionService.convert(
                            source.getPromotion(),
                            com.example.service.catalog.model.PublicPromotionModel.class))
                    .original(conversionService.convert(
                            source.getOriginal(),
                            com.example.service.catalog.model.PublicPromotionImageModel.class))
                    .build();
        }
    }
}
