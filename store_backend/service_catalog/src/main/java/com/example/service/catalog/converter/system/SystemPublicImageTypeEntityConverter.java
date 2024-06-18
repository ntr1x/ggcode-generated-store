package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicImageTypeEntity;
import com.example.service.catalog.model.system.SystemPublicImageTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicImageTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicImageTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicImageTypeEntity, SystemPublicImageTypeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicImageTypeModel convert(PublicImageTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicImageTypeModel.builder()
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
