package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicTemplateEntity;
import com.example.service.events.model.system.SystemPublicTemplateModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicTemplateEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicTemplateEntityConverter.toModel")
    class ToModel implements AppConverter<PublicTemplateEntity, SystemPublicTemplateModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicTemplateModel convert(PublicTemplateEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicTemplateModel.builder()
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
