package com.example.service.catalog.converter.system;

import lombok.RequiredArgsConstructor;

import com.example.service.catalog.entity.PublicPromotionTargetEntity;
import com.example.service.catalog.model.system.SystemPublicPromotionTargetModel;

import org.ntr1x.common.api.component.AppConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

public interface SystemPublicPromotionTargetEntityConverter {

    @RequiredArgsConstructor
    @Component("serviceCatalog.systemPublicPromotionTargetEntityConverter.toModel")
    class ToModel implements AppConverter<PublicPromotionTargetEntity, SystemPublicPromotionTargetModel> {
        private final ApplicationContext applicationContext;

        public SystemPublicPromotionTargetModel convert(PublicPromotionTargetEntity source) {
            ConversionService conversionService = applicationContext.getBean(ConversionService.class);

            return SystemPublicPromotionTargetModel.builder()
                    .id(source.getId())
                    .sourceTypeId(source.getSourceTypeId())
                    .promotionId(source.getPromotionId())
                    .categoryId(source.getCategoryId())
                    .productId(source.getProductId())
                    .regionId(source.getRegionId())
                    .shopId(source.getShopId())
                    .sourceType(conversionService.convert(
                            source.getSourceType(),
                            com.example.service.catalog.model.PublicSourceTypeModel.class))
                    .promotion(conversionService.convert(
                            source.getPromotion(),
                            com.example.service.catalog.model.PublicPromotionModel.class))
                    .category(conversionService.convert(
                            source.getCategory(),
                            com.example.service.catalog.model.PublicCategoryModel.class))
                    .product(conversionService.convert(
                            source.getProduct(),
                            com.example.service.catalog.model.PublicProductModel.class))
                    .region(conversionService.convert(
                            source.getRegion(),
                            com.example.service.catalog.model.PublicRegionModel.class))
                    .shop(conversionService.convert(
                            source.getShop(),
                            com.example.service.catalog.model.PublicShopModel.class))
                    .build();
        }
    }
}
