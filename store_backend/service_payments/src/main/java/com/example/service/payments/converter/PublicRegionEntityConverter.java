package com.example.service.payments.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.payments.entity.PublicRegionEntity;
import com.example.service.payments.model.PublicRegionModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicRegionEntityConverter {

    @RequiredArgsConstructor
    @Component("servicePayments.publicRegionEntityConverter.toModel")
    class ToModel implements AppConverter<PublicRegionEntity, PublicRegionModel> {
        private final ApplicationContext applicationContext;

        public PublicRegionModel convert(PublicRegionEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicRegionModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
