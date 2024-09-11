package com.example.service.catalog.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicProductAttachmentEntity;
import com.example.service.catalog.model.PublicProductAttachmentModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicProductAttachmentEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.publicProductAttachmentEntityConverter.toModel")
    class ToModel implements AppConverter<PublicProductAttachmentEntity, PublicProductAttachmentModel> {
        private final ApplicationContext applicationContext;

        public PublicProductAttachmentModel convert(PublicProductAttachmentEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicProductAttachmentModel.builder()
                    .id(source.getId())
                    .uri(source.getUri())
                    .size(source.getSize())
                    .pageCount(source.getPageCount())
                    .name(source.getName())
                    .typeId(source.getTypeId())
                    .productId(source.getProductId())
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.catalog.model.PublicAttachmentTypeModel.class))
                    .product(conversionService.convert(
                            source.getProduct(),
                            com.example.service.catalog.model.PublicProductModel.class))
                    .build();
        }
    }
}
