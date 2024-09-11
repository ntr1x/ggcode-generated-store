package com.example.service.catalog.converter.anonymous;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicRegionEntity;
import com.example.service.catalog.model.anonymous.AnonymousPublicRegionModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface AnonymousPublicRegionEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.anonymousPublicRegionEntityConverter.toModel")
    class ToModel implements AppConverter<PublicRegionEntity, AnonymousPublicRegionModel> {
        private final ApplicationContext applicationContext;

        public AnonymousPublicRegionModel convert(PublicRegionEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return AnonymousPublicRegionModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
