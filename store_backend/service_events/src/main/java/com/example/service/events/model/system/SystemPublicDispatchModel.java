package com.example.service.events.model.system;

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
public class SystemPublicDispatchModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.util.UUID customerId;
    
    @JsonView(Views.Default.class)
    private java.lang.String sessionId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID subscriptionId;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer typeId;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer statusId;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime createdAt;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime updatedAt;
    
    @JsonView(Views.Default.class)
    private com.fasterxml.jackson.databind.JsonNode payload;
    
    @JsonView(Views.Default.class)
    private com.example.service.events.model.PublicCustomerModel customer;
    
    @JsonView(Views.Default.class)
    private com.example.service.events.model.PublicSubscriptionModel subscription;
    
    @JsonView(Views.Default.class)
    private com.example.service.events.model.PublicDispatchTypeModel type;
    
    @JsonView(Views.Default.class)
    private com.example.service.events.model.PublicDispatchStatusModel status;
}
