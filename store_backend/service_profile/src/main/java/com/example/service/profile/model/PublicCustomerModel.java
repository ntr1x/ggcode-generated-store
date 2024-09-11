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
public class PublicCustomerModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.lang.String email;
    
    @JsonView(Views.Default.class)
    private java.lang.String phone;
    
    @JsonView(Views.Default.class)
    private java.lang.String name;
    
    @JsonView(Views.Default.class)
    private java.lang.String surname;
    
    @JsonView(Views.Default.class)
    private java.lang.String patronymic;
    
    @JsonView(Views.Default.class)
    private java.lang.String fullName;
}
