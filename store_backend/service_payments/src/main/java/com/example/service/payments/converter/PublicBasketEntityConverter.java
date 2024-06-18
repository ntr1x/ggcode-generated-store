package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicBasketEntity;
import com.example.service.payments.model.PublicBasketModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicBasketEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicBasketEntityConverter.toModel")
    class ToModel implements AppConverter<PublicBasketEntity, PublicBasketModel> {
        private final ApplicationContext applicationContext;

        public PublicBasketModel convert(PublicBasketEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicBasketModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .agentId(source.getAgentId())
                    .regionId(source.getRegionId())
                    .shopId(source.getShopId())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.payments.model.PublicCustomerModel.class))
                    .agent(conversionService.convert(
                            source.getAgent(),
                            com.example.service.payments.model.PublicAgentModel.class))
                    .region(conversionService.convert(
                            source.getRegion(),
                            com.example.service.payments.model.PublicRegionModel.class))
                    .shop(conversionService.convert(
                            source.getShop(),
                            com.example.service.payments.model.PublicShopModel.class))
                    .build();
        }
    }
}
