package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicOrderEntity;
import com.example.service.payments.model.PublicOrderModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicOrderEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicOrderEntityConverter.toModel")
    class ToModel implements AppConverter<PublicOrderEntity, PublicOrderModel> {
        private final ApplicationContext applicationContext;

        public PublicOrderModel convert(PublicOrderEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicOrderModel.builder()
                    .id(source.getId())
                    .sourceTypeId(source.getSourceTypeId())
                    .customerId(source.getCustomerId())
                    .regionId(source.getRegionId())
                    .shopId(source.getShopId())
                    .basketId(source.getBasketId())
                    .agentId(source.getAgentId())
                    .orderTypeId(source.getOrderTypeId())
                    .orderStatusId(source.getOrderStatusId())
                    .price(source.getPrice())
                    .spend(source.getSpend())
                    .bonus(source.getBonus())
                    .discount(source.getDiscount())
                    .createdAt(source.getCreatedAt())
                    .updatedAt(source.getUpdatedAt())
                    .sourceType(conversionService.convert(
                            source.getSourceType(),
                            com.example.service.payments.model.PublicSourceTypeModel.class))
                    .region(conversionService.convert(
                            source.getRegion(),
                            com.example.service.payments.model.PublicRegionModel.class))
                    .shop(conversionService.convert(
                            source.getShop(),
                            com.example.service.payments.model.PublicShopModel.class))
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.payments.model.PublicCustomerModel.class))
                    .basket(conversionService.convert(
                            source.getBasket(),
                            com.example.service.payments.model.PublicBasketModel.class))
                    .agent(conversionService.convert(
                            source.getAgent(),
                            com.example.service.payments.model.PublicAgentModel.class))
                    .orderType(conversionService.convert(
                            source.getOrderType(),
                            com.example.service.payments.model.PublicOrderTypeModel.class))
                    .orderStatus(conversionService.convert(
                            source.getOrderStatus(),
                            com.example.service.payments.model.PublicOrderStatusModel.class))
                    .build();
        }
    }
}
