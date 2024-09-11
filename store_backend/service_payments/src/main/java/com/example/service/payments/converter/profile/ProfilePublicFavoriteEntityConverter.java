package com.example.service.payments.converter.profile;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicFavoriteEntity;
import com.example.service.payments.model.profile.ProfilePublicFavoriteModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface ProfilePublicFavoriteEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.profilePublicFavoriteEntityConverter.toModel")
    class ToModel implements AppConverter<PublicFavoriteEntity, ProfilePublicFavoriteModel> {
        private final ApplicationContext applicationContext;

        public ProfilePublicFavoriteModel convert(PublicFavoriteEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return ProfilePublicFavoriteModel.builder()
                    .id(source.getId())
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
