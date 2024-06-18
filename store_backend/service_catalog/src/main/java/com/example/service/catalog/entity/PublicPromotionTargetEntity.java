package com.example.service.catalog.entity;

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
@Entity(name = "ServiceCatalogPublicPromotionTargetEntity")
@Table(schema = "public", name = "promotion_target")
public class PublicPromotionTargetEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "source_type_id")
    private java.lang.Integer sourceTypeId;
    
    @Column(name = "promotion_id", columnDefinition = "uuid")
    private java.util.UUID promotionId;
    
    @Column(name = "category_id", columnDefinition = "uuid")
    private java.util.UUID categoryId;
    
    @Column(name = "product_id", columnDefinition = "uuid")
    private java.util.UUID productId;
    
    @Column(name = "region_id", columnDefinition = "uuid")
    private java.util.UUID regionId;
    
    @Column(name = "shop_id", columnDefinition = "uuid")
    private java.util.UUID shopId;
    
    @ManyToOne
    @JoinColumn(name = "source_type_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicSourceTypeEntity sourceType;
    
    @ManyToOne
    @JoinColumn(name = "promotion_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicPromotionEntity promotion;
    
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicCategoryEntity category;
    
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicProductEntity product;
    
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicRegionEntity region;
    
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicShopEntity shop;
}
