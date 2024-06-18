package com.example.service.profile.converter.profile;

import lombok.RequiredArgsConstructor;

import com.example.service.profile.entity.PublicCustomerVerifyPhoneEntity;
import com.example.service.profile.model.profile.ProfilePublicCustomerVerifyPhoneModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface ProfilePublicCustomerVerifyPhoneEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceProfile.profilePublicCustomerVerifyPhoneEntityConverter.toModel")
    class ToModel implements AppConverter<PublicCustomerVerifyPhoneEntity, ProfilePublicCustomerVerifyPhoneModel> {
        private final ApplicationContext applicationContext;

        public ProfilePublicCustomerVerifyPhoneModel convert(PublicCustomerVerifyPhoneEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return ProfilePublicCustomerVerifyPhoneModel.builder()
                    .id(source.getId())
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
