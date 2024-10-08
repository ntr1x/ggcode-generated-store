package com.example.service.payments.model;

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
public class PublicPaymentModel {
    
    @JsonView(Views.Default.class)
    private java.util.UUID id;
    
    @JsonView(Views.Default.class)
    private java.util.UUID customerId;
    
    @JsonView(Views.Default.class)
    private java.util.UUID orderId;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer paymentStatusId;
    
    @JsonView(Views.Default.class)
    private java.lang.Integer paymentTypeId;
    
    @JsonView(Views.Default.class)
    private java.math.BigDecimal value;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime createdAt;
    
    @JsonView(Views.Default.class)
    private java.time.LocalDateTime updatedAt;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicCustomerModel customer;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicOrderModel order;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicPaymentStatusModel paymentStatus;
    
    @JsonView(Views.Default.class)
    private com.example.service.payments.model.PublicPaymentTypeModel paymentType;
}
