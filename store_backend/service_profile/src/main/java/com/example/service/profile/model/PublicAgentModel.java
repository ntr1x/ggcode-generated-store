package com.example.service.profile.model;

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
public class PublicAgentModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.util.UUID customerId;
    
    @JsonView(Views.Default.class)
    private java.lang.String kind;
    
    @JsonView(Views.Default.class)
    private java.lang.String title;
    
    @JsonView(Views.Default.class)
    private java.lang.String inn;
    
    @JsonView(Views.Default.class)
    private java.lang.String kpp;
    
    @JsonView(Views.Default.class)
    private java.lang.String bic;
    
    @JsonView(Views.Default.class)
    private java.lang.String bank;
    
    @JsonView(Views.Default.class)
    private java.lang.String okved;
    
    @JsonView(Views.Default.class)
    private java.lang.String address;
    
    @JsonView(Views.Default.class)
    private java.lang.String phone;
    
    @JsonView(Views.Default.class)
    private java.lang.String email;
    
    @JsonView(Views.Default.class)
    private java.lang.String registrationAddress;
    
    @JsonView(Views.Default.class)
    private com.example.service.profile.model.PublicCustomerModel customer;
}
