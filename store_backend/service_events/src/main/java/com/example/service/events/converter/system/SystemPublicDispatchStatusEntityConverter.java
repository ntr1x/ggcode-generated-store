package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicDispatchStatusEntity;
import com.example.service.events.model.system.SystemPublicDispatchStatusModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicDispatchStatusEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicDispatchStatusEntityConverter.toModel")
    class ToModel implements AppConverter<PublicDispatchStatusEntity, SystemPublicDispatchStatusModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicDispatchStatusModel convert(PublicDispatchStatusEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicDispatchStatusModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
