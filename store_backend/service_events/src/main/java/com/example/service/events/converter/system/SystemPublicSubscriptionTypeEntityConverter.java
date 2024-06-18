package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicSubscriptionTypeEntity;
import com.example.service.events.model.system.SystemPublicSubscriptionTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicSubscriptionTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicSubscriptionTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicSubscriptionTypeEntity, SystemPublicSubscriptionTypeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicSubscriptionTypeModel convert(PublicSubscriptionTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicSubscriptionTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
