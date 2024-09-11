package com.example.service.catalog.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicProductImageEntity;
import com.example.service.catalog.model.PublicProductImageModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicProductImageEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.publicProductImageEntityConverter.toModel")
    class ToModel implements AppConverter<PublicProductImageEntity, PublicProductImageModel> {
        private final ApplicationContext applicationContext;

        public PublicProductImageModel convert(PublicProductImageEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicProductImageModel.builder()
                    .id(source.getId())
                    .uri(source.getUri())
                    .name(source.getName())
                    .typeId(source.getTypeId())
                    .productId(source.getProductId())
                    .originalId(source.getOriginalId())
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.catalog.model.PublicImageTypeModel.class))
                    .product(conversionService.convert(
                            source.getProduct(),
                            com.example.service.catalog.model.PublicProductModel.class))
                    .original(conversionService.convert(
                            source.getOriginal(),
                            com.example.service.catalog.model.PublicProductImageModel.class))
                    .build();
        }
    }
}
