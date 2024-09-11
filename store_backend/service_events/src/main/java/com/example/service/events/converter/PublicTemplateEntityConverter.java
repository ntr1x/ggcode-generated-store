package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicTemplateEntity;
import com.example.service.events.model.PublicTemplateModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicTemplateEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicTemplateEntityConverter.toModel")
    class ToModel implements AppConverter<PublicTemplateEntity, PublicTemplateModel> {
        private final ApplicationContext applicationContext;

        public PublicTemplateModel convert(PublicTemplateEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicTemplateModel.builder()
                    .id(source.getId())
                    .typeId(source.getTypeId())
                    .shapeId(source.getShapeId())
                    .name(source.getName())
                    .payload(source.getPayload())
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.events.model.PublicTemplateTypeModel.class))
                    .shape(conversionService.convert(
                            source.getShape(),
                            com.example.service.events.model.PublicTemplateShapeModel.class))
                    .build();
        }
    }
}
