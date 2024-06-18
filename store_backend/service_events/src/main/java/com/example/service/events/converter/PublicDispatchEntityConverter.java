package com.example.service.events.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicDispatchEntity;
import com.example.service.events.model.PublicDispatchModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicDispatchEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.publicDispatchEntityConverter.toModel")
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
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.events.model.PublicCustomerModel.class))
                    .subscription(conversionService.convert(
                            source.getSubscription(),
                            com.example.service.events.model.PublicSubscriptionModel.class))
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.events.model.PublicDispatchTypeModel.class))
                    .status(conversionService.convert(
                            source.getStatus(),
                            com.example.service.events.model.PublicDispatchStatusModel.class))
                    .build();
        }
    }
}
