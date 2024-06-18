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
public class PublicImageTypeModel {
    
    @JsonView(Views.Default.class)
    private java.lang.Integer id;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private java.lang.String description;
    
    @JsonView(Views.Default.class)
    private java.lang.String format;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer width;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer height;
}
