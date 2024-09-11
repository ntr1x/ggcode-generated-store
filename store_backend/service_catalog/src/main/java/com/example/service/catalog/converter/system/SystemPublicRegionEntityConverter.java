package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicRegionEntity;
import com.example.service.catalog.model.system.SystemPublicRegionModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicRegionEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicRegionEntityConverter.toModel")
    class ToModel implements AppConverter<PublicRegionEntity, SystemPublicRegionModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicRegionModel convert(PublicRegionEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicRegionModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
