package com.example.service.structure.converter;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.entity.PublicNetworkTypeEntity;
import com.example.service.structure.model.PublicNetworkTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface PublicNetworkTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceStructure.publicNetworkTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicNetworkTypeEntity, PublicNetworkTypeModel> {
        private final ApplicationContext applicationContext;

        public PublicNetworkTypeModel convert(PublicNetworkTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return PublicNetworkTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
