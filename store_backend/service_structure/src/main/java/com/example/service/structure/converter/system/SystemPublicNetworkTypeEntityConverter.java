package com.example.service.structure.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.entity.PublicNetworkTypeEntity;
import com.example.service.structure.model.system.SystemPublicNetworkTypeModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicNetworkTypeEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceStructure.systemPublicNetworkTypeEntityConverter.toModel")
    class ToModel implements AppConverter<PublicNetworkTypeEntity, SystemPublicNetworkTypeModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicNetworkTypeModel convert(PublicNetworkTypeEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicNetworkTypeModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .build();
        }
    }
}
