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
@Entity(name = "GatewayEventsPublicCustomerEntity")
@Table(schema = "public", name = "customer")
public class PublicCustomerEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "email", columnDefinition = "text")
    private java.lang.String email;
    
    @Column(name = "phone", columnDefinition = "text")
    private java.lang.String phone;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "surname", columnDefinition = "text")
    private java.lang.String surname;
    
    @Column(name = "patronymic", columnDefinition = "text")
    private java.lang.String patronymic;
}
