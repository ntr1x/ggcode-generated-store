package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicEventTypeInfoEntity;
import com.example.service.events.model.PublicEventTypeInfoModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicEventTypeInfoEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicEventTypeInfoEntityConverter.toModel")
    class ToModel implements AppConverter<PublicEventTypeInfoEntity, PublicEventTypeInfoModel> {
        private final ApplicationContext applicationContext;

        public PublicEventTypeInfoModel convert(PublicEventTypeInfoEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicEventTypeInfoModel.builder()
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
