package com.example.service.payments.entity;

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
@Entity(name = "ServicePaymentsPublicBasketEntity")
@Table(schema = "public", name = "basket")
public class PublicBasketEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "customer_id", columnDefinition = "uuid")
    private java.util.UUID customerId;
    
    @Column(name = "agent_id", columnDefinition = "uuid")
    private java.util.UUID agentId;
    
    @Column(name = "region_id", columnDefinition = "uuid")
    private java.util.UUID regionId;
    
    @Column(name = "shop_id", columnDefinition = "uuid")
    private java.util.UUID shopId;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicCustomerEntity customer;
    
    @ManyToOne
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicAgentEntity agent;
    
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicRegionEntity region;
    
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicShopEntity shop;
}
