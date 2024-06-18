package com.example.service.profile.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.profile.entity.PublicCustomerEntity;
import com.example.service.profile.model.PublicCustomerModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicCustomerEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceProfile.publicCustomerEntityConverter.toModel")
    class ToModel implements AppConverter<PublicCustomerEntity, PublicCustomerModel> {
        private final ApplicationContext applicationContext;

        public PublicCustomerModel convert(PublicCustomerEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicCustomerModel.builder()
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
