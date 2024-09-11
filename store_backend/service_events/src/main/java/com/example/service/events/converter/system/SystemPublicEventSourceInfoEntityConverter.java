package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicEventSourceInfoEntity;
import com.example.service.events.model.system.SystemPublicEventSourceInfoModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicEventSourceInfoEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicEventSourceInfoEntityConverter.toModel")
    class ToModel implements AppConverter<PublicEventSourceInfoEntity, SystemPublicEventSourceInfoModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicEventSourceInfoModel convert(PublicEventSourceInfoEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicEventSourceInfoModel.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
