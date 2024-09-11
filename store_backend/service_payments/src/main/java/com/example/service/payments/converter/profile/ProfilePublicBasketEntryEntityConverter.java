package com.example.service.payments.converter.profile;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicBasketEntryEntity;
import com.example.service.payments.model.profile.ProfilePublicBasketEntryModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface ProfilePublicBasketEntryEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.profilePublicBasketEntryEntityConverter.toModel")
    class ToModel implements AppConverter<PublicBasketEntryEntity, ProfilePublicBasketEntryModel> {
        private final ApplicationContext applicationContext;

        public ProfilePublicBasketEntryModel convert(PublicBasketEntryEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return ProfilePublicBasketEntryModel.builder()
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
