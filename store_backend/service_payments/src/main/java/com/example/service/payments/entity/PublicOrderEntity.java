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
@Entity(name = "ServicePaymentsPublicOrderEntity")
@Table(schema = "public", name = "order")
public class PublicOrderEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "source_type_id")
    private java.lang.Integer sourceTypeId;
    
    @Column(name = "customer_id", columnDefinition = "uuid")
    private java.util.UUID customerId;
    
    @Column(name = "region_id", columnDefinition = "uuid")
    private java.util.UUID regionId;
    
    @Column(name = "shop_id", columnDefinition = "uuid")
    private java.util.UUID shopId;
    
    @Column(name = "basket_id", columnDefinition = "uuid")
    private java.util.UUID basketId;
    
    @Column(name = "agent_id", columnDefinition = "uuid")
    private java.util.UUID agentId;
    
    @Column(name = "order_type_id")
    private java.lang.Integer orderTypeId;
    
    @Column(name = "order_status_id")
    private java.lang.Integer orderStatusId;
    
    @Column(name = "price")
    private java.math.BigDecimal price;
    
    @Column(name = "spend")
    private java.math.BigDecimal spend;
    
    @Column(name = "bonus")
    private java.math.BigDecimal bonus;
    
    @Column(name = "discount")
    private java.math.BigDecimal discount;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "source_type_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicSourceTypeEntity sourceType;
    
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicRegionEntity region;
    
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicShopEntity shop;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicCustomerEntity customer;
    
    @ManyToOne
    @JoinColumn(name = "basket_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicBasketEntity basket;
    
    @ManyToOne
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicAgentEntity agent;
    
    @ManyToOne
    @JoinColumn(name = "order_type_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicOrderTypeEntity orderType;
    
    @ManyToOne
    @JoinColumn(name = "order_status_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicOrderStatusEntity orderStatus;
}
