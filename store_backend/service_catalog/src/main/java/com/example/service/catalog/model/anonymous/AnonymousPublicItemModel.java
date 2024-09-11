package com.example.service.catalog.model.anonymous;

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
public class AnonymousPublicItemModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.util.UUID productId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID shopId;
    
    @JsonView(Views.Default.class)
    private java.lang.String ean13;
    
    @JsonView(Views.Default.class)
    private java.lang.String code128;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicProductModel product;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicShopModel shop;
}
