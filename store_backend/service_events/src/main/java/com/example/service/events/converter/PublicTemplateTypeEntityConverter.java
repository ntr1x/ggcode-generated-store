package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicTemplateTypeEntity;
import com.example.service.events.model.PublicTemplateTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicTemplateTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicTemplateTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicTemplateTypeEntity, PublicTemplateTypeModel> {
        private final ApplicationContext applicationContext;

        public PublicTemplateTypeModel convert(PublicTemplateTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicTemplateTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
