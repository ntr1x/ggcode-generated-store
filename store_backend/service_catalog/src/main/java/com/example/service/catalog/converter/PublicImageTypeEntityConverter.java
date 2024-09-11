package com.example.service.catalog.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicImageTypeEntity;
import com.example.service.catalog.model.PublicImageTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicImageTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.publicImageTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicImageTypeEntity, PublicImageTypeModel> {
        private final ApplicationContext applicationContext;

        public PublicImageTypeModel convert(PublicImageTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicImageTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .format(source.getFormat())
                    .width(source.getWidth())
                    .height(source.getHeight())
                    .build();
        }
    }
}
