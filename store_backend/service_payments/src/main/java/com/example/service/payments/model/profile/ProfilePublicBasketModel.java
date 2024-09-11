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
public class ProfilePublicBasketModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.util.UUID agentId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID regionId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID shopId;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicCustomerModel customer;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicAgentModel agent;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicRegionModel region;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicShopModel shop;
}
