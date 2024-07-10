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
@Entity(name = "ServiceEventsPublicDispatchEntity")
@Table(schema = "public", name = "dispatch")
public class PublicDispatchEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "customer_id", columnDefinition = "uuid")
    private java.util.UUID customerId;
    
    @Column(name = "session_id", columnDefinition = "text")
    private java.lang.String sessionId;
    
    @Column(name = "subscription_id", columnDefinition = "uuid")
    private java.util.UUID subscriptionId;
    
    @Column(name = "type_id")
    private java.lang.Integer typeId;
    
    @Column(name = "status_id")
    private java.lang.Integer statusId;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
    
    @Column(name = "payload", columnDefinition = "jsonb")
    @Type(io.hypersistence.utils.hibernate.type.json.JsonBinaryType.class)
    @Lob
    private com.fasterxml.jackson.databind.JsonNode payload;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicCustomerEntity customer;
    
    @ManyToOne
    @JoinColumn(name = "subscription_id", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicSubscriptionEntity subscription;
    
    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicDispatchTypeEntity type;
    
    @ManyToOne
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private com.example.service.events.entity.PublicDispatchStatusEntity status;
}
