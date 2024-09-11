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
public class PublicPromotionImageModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.lang.String uri;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer typeId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID promotionId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID originalId;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicImageTypeModel type;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicPromotionModel promotion;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicPromotionImageModel original;
}
