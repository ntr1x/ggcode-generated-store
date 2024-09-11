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
public class SystemPublicGuideModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.util.UUID subjectId;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private java.lang.String link;
    
    @JsonView(Views.Default.class)
    private java.lang.String description;
    
    @JsonView(Views.Default.class)
    private java.lang.String attachment;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer position;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime createdAt;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime updatedAt;
    
    @JsonView(Views.Default.class)
    private com.example.service.structure.model.PublicGuideSubjectModel subject;
}
