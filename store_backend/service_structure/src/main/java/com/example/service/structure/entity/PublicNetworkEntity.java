package com.example.service.structure.entity;

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
@Entity(name = "ServiceStructurePublicNetworkEntity")
@Table(schema = "public", name = "network")
public class PublicNetworkEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "password", columnDefinition = "text")
    private java.lang.String password;
    
    @Column(name = "hidden")
    private java.lang.Boolean hidden;
    
    @Column(name = "shop_id", columnDefinition = "uuid")
    private java.util.UUID shopId;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private com.example.service.structure.entity.PublicNetworkTypeEntity type;
    
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private com.example.service.structure.entity.PublicShopEntity shop;
}
