package com.example.gateway.events.model;

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
public class UpdatePhoneModel {

    @JsonView(Views.Default.class)
    private java.util.UUID customerId;

    @JsonView(Views.Default.class)
    private java.lang.String phone;
}
