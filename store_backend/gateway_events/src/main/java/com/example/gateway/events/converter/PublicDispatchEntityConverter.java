package com.example.gateway.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.gateway.events.entity.PublicDispatchEntity;
import com.example.gateway.events.model.PublicDispatchModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicDispatchEntityConverter {

    @RequiredArgsConstructor
    @Component("gatewayEvents.publicDispatchEntityConverter.toModel")
    class ToModel implements AppConverter<PublicDispatchEntity, PublicDispatchModel> {
        private final ApplicationContext applicationContext;

        public PublicDispatchModel convert(PublicDispatchEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicDispatchModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .sessionId(source.getSessionId())
                    .subscriptionId(source.getSubscriptionId())
                    .typeId(source.getTypeId())
                    .statusId(source.getStatusId())
                    .createdAt(source.getCreatedAt())
                    .updatedAt(source.getUpdatedAt())
                    .payload(source.getPayload())
                    .build();
        }
    }
}
