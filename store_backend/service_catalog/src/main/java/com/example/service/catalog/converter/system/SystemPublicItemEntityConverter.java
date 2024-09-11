package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicItemEntity;
import com.example.service.catalog.model.system.SystemPublicItemModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicItemEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicItemEntityConverter.toModel")
    class ToModel implements AppConverter<PublicItemEntity, SystemPublicItemModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicItemModel convert(PublicItemEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicItemModel.builder()
                    .id(source.getId())
                    .productId(source.getProductId())
                    .shopId(source.getShopId())
                    .ean13(source.getEan13())
                    .code128(source.getCode128())
                    .product(conversionService.convert(
                            source.getProduct(),
                            com.example.service.catalog.model.PublicProductModel.class))
                    .shop(conversionService.convert(
                            source.getShop(),
                            com.example.service.catalog.model.PublicShopModel.class))
                    .build();
        }
    }
}
