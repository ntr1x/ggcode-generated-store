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
@Entity(name = "ServiceEventsPublicEventSourceInfoEntity")
@Table(schema = "public", name = "event_source_info")
public class PublicEventSourceInfoEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "description", columnDefinition = "text")
    private java.lang.String description;
}
