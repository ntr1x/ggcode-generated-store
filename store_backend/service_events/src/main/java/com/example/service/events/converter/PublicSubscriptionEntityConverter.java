package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicSubscriptionEntity;
import com.example.service.events.model.PublicSubscriptionModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicSubscriptionEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicSubscriptionEntityConverter.toModel")
    class ToModel implements AppConverter<PublicSubscriptionEntity, PublicSubscriptionModel> {
        private final ApplicationContext applicationContext;

        public PublicSubscriptionModel convert(PublicSubscriptionEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicSubscriptionModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .typeId(source.getTypeId())
                    .sessionId(source.getSessionId())
                    .createdAt(source.getCreatedAt())
                    .payload(source.getPayload())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.events.model.PublicCustomerModel.class))
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.events.model.PublicSubscriptionTypeModel.class))
                    .build();
        }
    }
}
