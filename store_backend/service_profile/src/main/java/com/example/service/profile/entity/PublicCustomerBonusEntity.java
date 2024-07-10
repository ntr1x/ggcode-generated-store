package com.example.service.profile.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ServiceProfilePublicCustomerBonusEntity")
@Table(schema = "public", name = "customer_bonus")
public class PublicCustomerBonusEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "customer_id", columnDefinition = "uuid")
    private java.util.UUID customerId;
    
    @Column(name = "order_discount_id", columnDefinition = "uuid")
    private java.util.UUID orderDiscountId;
    
    @Column(name = "bonus")
    private java.math.BigDecimal bonus;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private com.example.service.profile.entity.PublicCustomerEntity customer;
}
