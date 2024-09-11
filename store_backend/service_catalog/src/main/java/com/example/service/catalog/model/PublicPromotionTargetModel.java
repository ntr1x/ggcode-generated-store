package com.example.service.catalog.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ntr1x.common.api.views.Views;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PublicPromotionTargetModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer sourceTypeId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID promotionId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID categoryId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID productId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID regionId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID shopId;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicSourceTypeModel sourceType;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicPromotionModel promotion;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicCategoryModel category;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicProductModel product;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicRegionModel region;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicShopModel shop;
}
