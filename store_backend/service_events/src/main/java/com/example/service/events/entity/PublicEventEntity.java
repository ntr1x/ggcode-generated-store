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
@Entity(name = "ServiceEventsPublicEventEntity")
@Table(schema = "public", name = "event")
public class PublicEventEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "topic", columnDefinition = "text")
    private java.lang.String topic;
    
    @Column(name = "content_type", columnDefinition = "text")
    private java.lang.String contentType;
    
    @Column(name = "ce_type", columnDefinition = "text")
    private java.lang.String ceType;
    
    @Column(name = "ce_source", columnDefinition = "text")
    private java.lang.String ceSource;
    
    @Column(name = "ce_specification", columnDefinition = "text")
    private java.lang.String ceSpecification;
    
    @Column(name = "ce_id", columnDefinition = "text")
    private java.lang.String ceId;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "payload", columnDefinition = "jsonb")
    @Type(io.hypersistence.utils.hibernate.type.json.JsonBinaryType.class)
    @Lob
    private com.fasterxml.jackson.databind.JsonNode payload;
    
    @ManyToOne
    @JoinColumn(name = "topic", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicEventTopicInfoEntity topicInfo;
    
    @ManyToOne
    @JoinColumn(name = "ce_source", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicEventSourceInfoEntity ceSourceInfo;
    
    @ManyToOne
    @JoinColumn(name = "ce_type", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicEventTypeInfoEntity ceTypeInfo;
}
