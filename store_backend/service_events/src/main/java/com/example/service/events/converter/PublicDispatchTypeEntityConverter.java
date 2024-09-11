package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicDispatchTypeEntity;
import com.example.service.events.model.PublicDispatchTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicDispatchTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicDispatchTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicDispatchTypeEntity, PublicDispatchTypeModel> {
        private final ApplicationContext applicationContext;

        public PublicDispatchTypeModel convert(PublicDispatchTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicDispatchTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
