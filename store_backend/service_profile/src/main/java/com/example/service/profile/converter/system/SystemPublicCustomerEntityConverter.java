package com.example.service.profile.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.profile.entity.PublicCustomerEntity;
import com.example.service.profile.model.system.SystemPublicCustomerModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicCustomerEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceProfile.systemPublicCustomerEntityConverter.toModel")
    class ToModel implements AppConverter<PublicCustomerEntity, SystemPublicCustomerModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicCustomerModel convert(PublicCustomerEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicCustomerModel.builder()
                    .id(source.getId())
                    .email(source.getEmail())
                    .phone(source.getPhone())
                    .name(source.getName())
                    .surname(source.getSurname())
                    .patronymic(source.getPatronymic())
                    .fullName(source.getFullName())
                    .build();
        }
    }
}
