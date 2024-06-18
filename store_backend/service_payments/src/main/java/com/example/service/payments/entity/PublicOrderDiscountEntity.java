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
@Entity(name = "ServicePaymentsPublicOrderDiscountEntity")
@Table(schema = "public", name = "order_discount")
public class PublicOrderDiscountEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "order_id", columnDefinition = "uuid")
    private java.util.UUID orderId;
    
    @Column(name = "product_id", columnDefinition = "uuid")
    private java.util.UUID productId;
    
    @Column(name = "discount")
    private java.math.BigDecimal discount;
    
    @Column(name = "bonus")
    private java.math.BigDecimal bonus;
    
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicOrderEntity order;
    
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicProductEntity product;
}
