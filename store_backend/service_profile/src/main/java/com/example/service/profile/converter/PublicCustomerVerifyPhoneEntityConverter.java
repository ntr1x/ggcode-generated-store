package com.example.service.profile.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.profile.entity.PublicCustomerVerifyPhoneEntity;
import com.example.service.profile.model.PublicCustomerVerifyPhoneModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicCustomerVerifyPhoneEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceProfile.publicCustomerVerifyPhoneEntityConverter.toModel")
    class ToModel implements AppConverter<PublicCustomerVerifyPhoneEntity, PublicCustomerVerifyPhoneModel> {
        private final ApplicationContext applicationContext;

        public PublicCustomerVerifyPhoneModel convert(PublicCustomerVerifyPhoneEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicCustomerVerifyPhoneModel.builder()
                    .id(source.getId())
                    .customerId(source.getCustomerId())
                    .phone(source.getPhone())
                    .secret(source.getSecret())
                    .isConfirmed(source.getIsConfirmed())
                    .customer(conversionService.convert(
                            source.getCustomer(),
                            com.example.service.profile.model.PublicCustomerModel.class))
                    .build();
        }
    }
}
