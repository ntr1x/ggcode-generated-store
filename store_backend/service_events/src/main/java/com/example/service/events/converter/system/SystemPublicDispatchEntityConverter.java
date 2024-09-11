package com.example.service.events.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.events.entity.PublicDispatchEntity;
import com.example.service.events.model.system.SystemPublicDispatchModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicDispatchEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceEvents.systemPublicDispatchEntityConverter.toModel")
    class ToModel implements AppConverter<PublicDispatchEntity, SystemPublicDispatchModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicDispatchModel convert(PublicDispatchEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicDispatchModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .sessionId(source.getSessionId())
                    .subscriptionId(source.getSubscriptionId())
                    .typeId(source.getTypeId())
                    .statusId(source.getStatusId())
                    .templateTypeId(source.getTemplateTypeId())
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
                    .templateType(conversionService.convert(
                            source.getTemplateType(),
                            com.example.service.events.model.PublicTemplateTypeModel.class))
                    .build();
        }
    }
}
