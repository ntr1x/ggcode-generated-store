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
@Entity(name = "ServicePaymentsPublicBasketEntryEntity")
@Table(schema = "public", name = "basket_entry")
public class PublicBasketEntryEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "basket_id", columnDefinition = "uuid")
    private java.util.UUID basketId;
    
    @Column(name = "product_id", columnDefinition = "uuid")
    private java.util.UUID productId;
    
    @Column(name = "quantity")
    private java.lang.Integer quantity;
    
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicProductEntity product;
    
    @ManyToOne
    @JoinColumn(name = "basket_id", insertable = false, updatable = false)
    private com.example.service.payments.entity.PublicBasketEntity basket;
}
