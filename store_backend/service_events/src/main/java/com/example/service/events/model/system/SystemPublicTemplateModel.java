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
public class SystemPublicTemplateModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer typeId;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer shapeId;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private com.fasterxml.jackson.databind.JsonNode payload;
    
    @JsonView(Views.Default.class)
    private com.example.service.events.model.PublicTemplateTypeModel type;
    
    @JsonView(Views.Default.class)
    private com.example.service.events.model.PublicTemplateShapeModel shape;
}
