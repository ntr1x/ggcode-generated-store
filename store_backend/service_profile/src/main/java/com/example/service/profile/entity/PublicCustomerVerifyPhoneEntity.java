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
@Entity(name = "ServiceProfilePublicCustomerVerifyPhoneEntity")
@Table(schema = "public", name = "customer_verify_phone")
public class PublicCustomerVerifyPhoneEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "customer_id", columnDefinition = "uuid")
    private java.util.UUID customerId;
    
    @Column(name = "phone", columnDefinition = "text")
    private java.lang.String phone;
    
    @Column(name = "secret")
    private java.lang.Integer secret;
    
    @Column(name = "is_confirmed")
    private java.lang.Boolean isConfirmed;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private com.example.service.profile.entity.PublicCustomerEntity customer;
}
