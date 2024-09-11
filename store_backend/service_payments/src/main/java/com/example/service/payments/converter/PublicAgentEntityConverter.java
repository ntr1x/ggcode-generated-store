package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicAgentEntity;
import com.example.service.payments.model.PublicAgentModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicAgentEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicAgentEntityConverter.toModel")
    class ToModel implements AppConverter<PublicAgentEntity, PublicAgentModel> {
        private final ApplicationContext applicationContext;

        public PublicAgentModel convert(PublicAgentEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicAgentModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .kind(source.getKind())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.payments.model.PublicCustomerModel.class))
                    .build();
        }
    }
}
