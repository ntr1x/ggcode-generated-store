package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicEventTypeInfoEntity;
import com.example.service.events.model.system.SystemPublicEventTypeInfoModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicEventTypeInfoEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicEventTypeInfoEntityConverter.toModel")
    class ToModel implements AppConverter<PublicEventTypeInfoEntity, SystemPublicEventTypeInfoModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicEventTypeInfoModel convert(PublicEventTypeInfoEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicEventTypeInfoModel.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
