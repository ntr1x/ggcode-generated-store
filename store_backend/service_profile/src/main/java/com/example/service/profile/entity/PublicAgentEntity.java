package com.example.service.profile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ServiceProfilePublicAgentEntity")
@Table(schema = "public", name = "agent")
public class PublicAgentEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "customer_id", columnDefinition = "uuid")
    private java.util.UUID customerId;
    
    @Column(name = "kind", columnDefinition = "text")
    private java.lang.String kind;
    
    @Column(name = "title", columnDefinition = "text")
    private java.lang.String title;
    
    @Column(name = "inn", columnDefinition = "text")
    private java.lang.String inn;
    
    @Column(name = "kpp", columnDefinition = "text")
    private java.lang.String kpp;
    
    @Column(name = "bic", columnDefinition = "text")
    private java.lang.String bic;
    
    @Column(name = "bank", columnDefinition = "text")
    private java.lang.String bank;
    
    @Column(name = "okved", columnDefinition = "text")
    private java.lang.String okved;
    
    @Column(name = "address", columnDefinition = "text")
    private java.lang.String address;
    
    @Column(name = "phone", columnDefinition = "text")
    private java.lang.String phone;
    
    @Column(name = "email", columnDefinition = "text")
    private java.lang.String email;
    
    @Column(name = "registration_address", columnDefinition = "text")
    private java.lang.String registrationAddress;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private com.example.service.profile.entity.PublicCustomerEntity customer;
}
