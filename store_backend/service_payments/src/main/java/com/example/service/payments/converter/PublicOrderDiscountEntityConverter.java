package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicOrderDiscountEntity;
import com.example.service.payments.model.PublicOrderDiscountModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicOrderDiscountEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicOrderDiscountEntityConverter.toModel")
    class ToModel implements AppConverter<PublicOrderDiscountEntity, PublicOrderDiscountModel> {
        private final ApplicationContext applicationContext;

        public PublicOrderDiscountModel convert(PublicOrderDiscountEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicOrderDiscountModel.builder()
                    .id(source.getId())
                    .orderId(source.getOrderId())
                    .productId(source.getProductId())
                    .discount(source.getDiscount())
                    .bonus(source.getBonus())
                    .order(conversionService.convert(
                            source.getOrder(),
                            com.example.service.payments.model.PublicOrderModel.class))
                    .product(conversionService.convert(
                            source.getProduct(),
                            com.example.service.payments.model.PublicProductModel.class))
                    .build();
        }
    }
}
