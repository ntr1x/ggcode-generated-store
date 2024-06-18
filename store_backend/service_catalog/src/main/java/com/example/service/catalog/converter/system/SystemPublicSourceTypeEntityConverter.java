package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicSourceTypeEntity;
import com.example.service.catalog.model.system.SystemPublicSourceTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicSourceTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicSourceTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicSourceTypeEntity, SystemPublicSourceTypeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicSourceTypeModel convert(PublicSourceTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicSourceTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
