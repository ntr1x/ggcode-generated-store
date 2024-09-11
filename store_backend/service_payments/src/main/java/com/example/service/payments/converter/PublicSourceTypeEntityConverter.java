package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicSourceTypeEntity;
import com.example.service.payments.model.PublicSourceTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicSourceTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicSourceTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicSourceTypeEntity, PublicSourceTypeModel> {
        private final ApplicationContext applicationContext;

        public PublicSourceTypeModel convert(PublicSourceTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicSourceTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
