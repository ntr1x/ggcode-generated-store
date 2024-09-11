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
@Entity(name = "ServiceCatalogPublicProductEntity")
@Table(schema = "public", name = "product")
public class PublicProductEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "description", columnDefinition = "text")
    private java.lang.String description;
    
    @Column(name = "price")
    private java.math.BigDecimal price;
    
    @Column(name = "ean_13", columnDefinition = "text")
    private java.lang.String ean13;
    
    @Column(name = "category_id", columnDefinition = "uuid")
    private java.util.UUID categoryId;
    
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicCategoryEntity category;
    
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<com.example.service.catalog.entity.PublicProductImageEntity> images;
    
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<com.example.service.catalog.entity.PublicProductAttachmentEntity> attachments;
}
