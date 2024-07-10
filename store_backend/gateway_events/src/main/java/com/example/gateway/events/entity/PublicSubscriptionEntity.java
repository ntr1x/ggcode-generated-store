package com.example.gateway.events.entity;

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
@Entity(name = "GatewayEventsPublicSubscriptionEntity")
@Table(schema = "public", name = "subscription")
public class PublicSubscriptionEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "customer_id", columnDefinition = "uuid")
    private java.util.UUID customerId;
    
    @Column(name = "type_id")
    private java.lang.Integer typeId;
    
    @Column(name = "session_id", columnDefinition = "text")
    private java.lang.String sessionId;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "payload", columnDefinition = "jsonb")
    @Type(io.hypersistence.utils.hibernate.type.json.JsonBinaryType.class)
    @Lob
    private com.fasterxml.jackson.databind.JsonNode payload;
}
