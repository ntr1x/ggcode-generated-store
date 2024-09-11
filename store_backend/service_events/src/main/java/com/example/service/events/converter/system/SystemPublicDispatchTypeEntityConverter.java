package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicDispatchTypeEntity;
import com.example.service.events.model.system.SystemPublicDispatchTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicDispatchTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicDispatchTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicDispatchTypeEntity, SystemPublicDispatchTypeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicDispatchTypeModel convert(PublicDispatchTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicDispatchTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
