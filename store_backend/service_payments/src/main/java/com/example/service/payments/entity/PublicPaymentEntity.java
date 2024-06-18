package com.example.service.payments.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ServicePaymentsPublicPaymentEntity")
@Table(schema = "public", name = "payment")
public class PublicPaymentEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "customer_id", columnDefinition = "uuid")
    private java.util.UUID customerId;
    
    @Column(name = "order_id", columnDefinition = "uuid")
    private java.util.UUID orderId;
    
    @Column(name = "payment_status_id")
    private java.lang.Integer paymentStatusId;
    
    @Column(name = "payment_type_id")
    private java.lang.Integer paymentTypeId;
    
    @Column(name = "value")
    private java.math.BigDecimal value;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicCustomerEntity customer;
    
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicOrderEntity order;
    
    @ManyToOne
    @JoinColumn(name = "payment_status_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicPaymentStatusEntity paymentStatus;
    
    @ManyToOne
    @JoinColumn(name = "payment_type_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicPaymentTypeEntity paymentType;
}
