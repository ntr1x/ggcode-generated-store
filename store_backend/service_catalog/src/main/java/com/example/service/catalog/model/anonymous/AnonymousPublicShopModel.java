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
public class AnonymousPublicShopModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private java.lang.String description;
    
    @JsonView(Views.Default.class)
    private java.lang.String address;
    
    @JsonView(Views.Default.class)
    private java.math.BigDecimal lon;
    
    @JsonView(Views.Default.class)
    private java.math.BigDecimal lat;
    
    @JsonView(Views.Default.class)
    private java.util.UUID regionId;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicRegionModel region;
}
