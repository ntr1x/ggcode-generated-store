package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicBasketEntryEntity;
import com.example.service.payments.model.PublicBasketEntryModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicBasketEntryEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicBasketEntryEntityConverter.toModel")
    class ToModel implements AppConverter<PublicBasketEntryEntity, PublicBasketEntryModel> {
        private final ApplicationContext applicationContext;

        public PublicBasketEntryModel convert(PublicBasketEntryEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicBasketEntryModel.builder()
                    .id(source.getId())
                    .basketId(source.getBasketId())
                    .productId(source.getProductId())
                    .quantity(source.getQuantity())
                    .product(conversionService.convert(
                            source.getProduct(),
                            com.example.service.payments.model.PublicProductModel.class))
                    .basket(conversionService.convert(
                            source.getBasket(),
                            com.example.service.payments.model.PublicBasketModel.class))
                    .build();
        }
    }
}
