package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicTemplateShapeEntity;
import com.example.service.events.model.PublicTemplateShapeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicTemplateShapeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicTemplateShapeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicTemplateShapeEntity, PublicTemplateShapeModel> {
        private final ApplicationContext applicationContext;

        public PublicTemplateShapeModel convert(PublicTemplateShapeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicTemplateShapeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
