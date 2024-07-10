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
@Entity(name = "ServiceCatalogPublicProductAttachmentEntity")
@Table(schema = "public", name = "product_attachment")
public class PublicProductAttachmentEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "uri", columnDefinition = "text")
    private java.lang.String uri;
    
    @Column(name = "size")
    private java.lang.Integer size;
    
    @Column(name = "page_count")
    private java.lang.Integer pageCount;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "type_id")
    private java.lang.Integer typeId;
    
    @Column(name = "product_id", columnDefinition = "uuid")
    private java.util.UUID productId;
    
    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicAttachmentTypeEntity type;
    
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private com.example.service.catalog.entity.PublicProductEntity product;
}
