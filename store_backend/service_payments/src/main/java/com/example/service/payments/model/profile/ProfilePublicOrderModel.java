package com.example.service.payments.model.profile;

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
public class ProfilePublicOrderModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer sourceTypeId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID regionId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID shopId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID basketId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID agentId;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer orderTypeId;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer orderStatusId;
    
    @JsonView(Views.Default.class)
    private java.math.BigDecimal price;
    
    @JsonView(Views.Default.class)
    private java.math.BigDecimal spend;
    
    @JsonView(Views.Default.class)
    private java.math.BigDecimal bonus;
    
    @JsonView(Views.Default.class)
    private java.math.BigDecimal discount;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime createdAt;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime updatedAt;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicSourceTypeModel sourceType;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicRegionModel region;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicShopModel shop;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicCustomerModel customer;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicBasketModel basket;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicAgentModel agent;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicOrderTypeModel orderType;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicOrderStatusModel orderStatus;
}
