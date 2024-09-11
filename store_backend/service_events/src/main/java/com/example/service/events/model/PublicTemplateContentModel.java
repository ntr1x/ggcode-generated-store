package com.example.service.events.model;

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
public class PublicTemplateContentModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.util.UUID templateId;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer typeId;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private com.fasterxml.jackson.databind.JsonNode payload;
    
    @JsonView(Views.Default.class)
    private com.example.service.events.model.PublicTemplateModel template;
    
    @JsonView(Views.Default.class)
    private com.example.service.events.model.PublicTemplateContentTypeModel type;
}
