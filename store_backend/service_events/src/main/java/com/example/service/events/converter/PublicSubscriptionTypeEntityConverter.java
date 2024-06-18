package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicSubscriptionTypeEntity;
import com.example.service.events.model.PublicSubscriptionTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicSubscriptionTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicSubscriptionTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicSubscriptionTypeEntity, PublicSubscriptionTypeModel> {
        private final ApplicationContext applicationContext;

        public PublicSubscriptionTypeModel convert(PublicSubscriptionTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicSubscriptionTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
