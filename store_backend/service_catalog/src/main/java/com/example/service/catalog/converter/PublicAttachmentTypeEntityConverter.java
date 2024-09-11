package com.example.service.catalog.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicAttachmentTypeEntity;
import com.example.service.catalog.model.PublicAttachmentTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicAttachmentTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.publicAttachmentTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicAttachmentTypeEntity, PublicAttachmentTypeModel> {
        private final ApplicationContext applicationContext;

        public PublicAttachmentTypeModel convert(PublicAttachmentTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicAttachmentTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
