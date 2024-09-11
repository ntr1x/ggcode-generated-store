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
@Entity(name = "ServiceCatalogPublicImageTypeEntity")
@Table(schema = "public", name = "image_type")
public class PublicImageTypeEntity {
    
    @Id
    @Column(updatable = false)
    private java.lang.Integer id;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "description", columnDefinition = "text")
    private java.lang.String description;
    
    @Column(name = "format", columnDefinition = "text")
    private java.lang.String format;
    
    @Column(name = "width")
    private java.lang.Integer width;
    
    @Column(name = "height")
    private java.lang.Integer height;
}
