package com.example.service.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.profile.entity.PublicCustomerVerifyPhoneEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicCustomerVerifyPhoneRepositoryProto extends JpaRepository<PublicCustomerVerifyPhoneEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCustomerVerifyPhoneEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
