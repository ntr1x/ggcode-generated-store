package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicEventSourceInfoEntity;
import com.example.service.events.model.PublicEventSourceInfoModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicEventSourceInfoEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicEventSourceInfoEntityConverter.toModel")
    class ToModel implements AppConverter<PublicEventSourceInfoEntity, PublicEventSourceInfoModel> {
        private final ApplicationContext applicationContext;

        public PublicEventSourceInfoModel convert(PublicEventSourceInfoEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicEventSourceInfoModel.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
