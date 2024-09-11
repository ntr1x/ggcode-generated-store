package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicDispatchStatusEntity;
import com.example.service.events.model.PublicDispatchStatusModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicDispatchStatusEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicDispatchStatusEntityConverter.toModel")
    class ToModel implements AppConverter<PublicDispatchStatusEntity, PublicDispatchStatusModel> {
        private final ApplicationContext applicationContext;

        public PublicDispatchStatusModel convert(PublicDispatchStatusEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicDispatchStatusModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
