package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicTemplateShapeEntity;
import com.example.service.events.model.system.SystemPublicTemplateShapeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicTemplateShapeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicTemplateShapeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicTemplateShapeEntity, SystemPublicTemplateShapeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicTemplateShapeModel convert(PublicTemplateShapeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicTemplateShapeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
