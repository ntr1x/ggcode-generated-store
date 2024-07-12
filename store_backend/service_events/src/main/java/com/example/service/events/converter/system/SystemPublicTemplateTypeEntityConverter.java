package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicTemplateTypeEntity;
import com.example.service.events.model.system.SystemPublicTemplateTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicTemplateTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicTemplateTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicTemplateTypeEntity, SystemPublicTemplateTypeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicTemplateTypeModel convert(PublicTemplateTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicTemplateTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
