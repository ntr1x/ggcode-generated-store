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
@Entity(name = "ServiceCatalogPublicShopEntity")
@Table(schema = "public", name = "shop")
public class PublicShopEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "description", columnDefinition = "text")
    private java.lang.String description;
    
    @Column(name = "address", columnDefinition = "text")
    private java.lang.String address;
    
    @Column(name = "lon")
    private java.math.BigDecimal lon;
    
    @Column(name = "lat")
    private java.math.BigDecimal lat;
    
    @Column(name = "region_id", columnDefinition = "uuid")
    private java.util.UUID regionId;
    
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicRegionEntity region;
}
