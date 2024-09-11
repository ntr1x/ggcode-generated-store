package com.example.service.events.entity;

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
@Entity(name = "ServiceEventsPublicTemplateEntity")
@Table(schema = "public", name = "template")
public class PublicTemplateEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "type_id")
    private java.lang.Integer typeId;
    
    @Column(name = "shape_id")
    private java.lang.Integer shapeId;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "payload", columnDefinition = "jsonb")
    @Type(io.hypersistence.utils.hibernate.type.json.JsonBinaryType.class)
    @Lob
    private com.fasterxml.jackson.databind.JsonNode payload;
    
    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicTemplateTypeEntity type;
    
    @ManyToOne
    @JoinColumn(name = "shape_id", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicTemplateShapeEntity shape;
}
