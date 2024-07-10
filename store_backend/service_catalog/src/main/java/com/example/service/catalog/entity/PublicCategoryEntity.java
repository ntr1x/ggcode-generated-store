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
@Entity(name = "ServiceCatalogPublicCategoryEntity")
@Table(schema = "public", name = "category")
public class PublicCategoryEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "description", columnDefinition = "text")
    private java.lang.String description;
}
