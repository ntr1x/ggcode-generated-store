package com.example.service.profile.converter.profile;

import lombok.RequiredArgsConstructor;

import com.example.service.profile.entity.PublicCustomerEntity;
import com.example.service.profile.model.profile.ProfilePublicCustomerModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface ProfilePublicCustomerEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceProfile.profilePublicCustomerEntityConverter.toModel")
    class ToModel implements AppConverter<PublicCustomerEntity, ProfilePublicCustomerModel> {
        private final ApplicationContext applicationContext;

        public ProfilePublicCustomerModel convert(PublicCustomerEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return ProfilePublicCustomerModel.builder()
                    .id(source.getId())
                    .email(source.getEmail())
                    .phone(source.getPhone())
                    .name(source.getName())
                    .surname(source.getSurname())
                    .patronymic(source.getPatronymic())
                    .build();
        }
    }
}
