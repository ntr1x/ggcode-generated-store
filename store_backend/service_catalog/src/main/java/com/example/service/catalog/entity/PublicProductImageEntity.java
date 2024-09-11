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
@Entity(name = "ServiceCatalogPublicProductImageEntity")
@Table(schema = "public", name = "product_image")
public class PublicProductImageEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "uri", columnDefinition = "text")
    private java.lang.String uri;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "type_id")
    private java.lang.Integer typeId;
    
    @Column(name = "product_id", columnDefinition = "uuid")
    private java.util.UUID productId;
    
    @Column(name = "original_id", columnDefinition = "uuid")
    private java.util.UUID originalId;
    
    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicImageTypeEntity type;
    
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicProductEntity product;
    
    @ManyToOne
    @JoinColumn(name = "original_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicProductImageEntity original;
}
