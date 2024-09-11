package com.example.service.catalog.entity;

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
@Entity(name = "ServiceCatalogPublicItemEntity")
@Table(schema = "public", name = "item")
public class PublicItemEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "product_id", columnDefinition = "uuid")
    private java.util.UUID productId;
    
    @Column(name = "shop_id", columnDefinition = "uuid")
    private java.util.UUID shopId;
    
    @Column(name = "ean_13", columnDefinition = "text")
    private java.lang.String ean13;
    
    @Column(name = "code_128", columnDefinition = "text")
    private java.lang.String code128;
    
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicProductEntity product;
    
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicShopEntity shop;
}
