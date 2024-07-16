package com.example.service.structure.model.system;

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
public class SystemPublicNetworkModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private java.lang.String password;
    
    @JsonView(Views.Default.class)
    private java.lang.Boolean hidden;
    
    @JsonView(Views.Default.class)
    private java.util.UUID shopId;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime createdAt;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime updatedAt;
    
    @JsonView(Views.Default.class)
    private com.example.service.structure.model.PublicNetworkTypeModel type;
    
    @JsonView(Views.Default.class)
    private com.example.service.structure.model.PublicShopModel shop;
}
