package com.example.service.structure.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.structure.entity.PublicNetworkEntity;
import com.example.service.structure.model.system.SystemPublicNetworkModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicNetworkEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceStructure.systemPublicNetworkEntityConverter.toModel")
    class ToModel implements AppConverter<PublicNetworkEntity, SystemPublicNetworkModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicNetworkModel convert(PublicNetworkEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicNetworkModel.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .password(source.getPassword())
                    .hidden(source.getHidden())
                    .shopId(source.getShopId())
                    .createdAt(source.getCreatedAt())
                    .updatedAt(source.getUpdatedAt())
                    .type(conversionService.convert(
                            source.getType(),
                            com.example.service.structure.model.PublicNetworkTypeModel.class))
                    .shop(conversionService.convert(
                            source.getShop(),
                            com.example.service.structure.model.PublicShopModel.class))
                    .build();
        }
    }
}
