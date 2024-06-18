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
@Entity(name = "ServiceProfilePublicCustomerEntity")
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
    
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<com.example.service.profile.entity.PublicAgentEntity> agents;
    
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<com.example.service.profile.entity.PublicCustomerVerifyPhoneEntity> phones;
    
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<com.example.service.profile.entity.PublicCustomerVerifyEmailEntity> emails;
    
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<com.example.service.profile.entity.PublicCustomerBonusEntity> bonuses;
}
