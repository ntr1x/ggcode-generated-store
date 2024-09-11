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
public class PublicProductAttachmentModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.lang.String uri;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer size;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer pageCount;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer typeId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID productId;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicAttachmentTypeModel type;
    
    @JsonView(Views.Default.class)
    private com.example.service.catalog.model.PublicProductModel product;
}
