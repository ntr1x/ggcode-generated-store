package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicFavoriteEntity;
import com.example.service.payments.model.PublicFavoriteModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicFavoriteEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicFavoriteEntityConverter.toModel")
    class ToModel implements AppConverter<PublicFavoriteEntity, PublicFavoriteModel> {
        private final ApplicationContext applicationContext;

        public PublicFavoriteModel convert(PublicFavoriteEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicFavoriteModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .productId(source.getProductId())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.payments.model.PublicCustomerModel.class))
                    .product(conversionService.convert(
                            source.getProduct(),
                            com.example.service.payments.model.PublicProductModel.class))
                    .build();
        }
    }
}
