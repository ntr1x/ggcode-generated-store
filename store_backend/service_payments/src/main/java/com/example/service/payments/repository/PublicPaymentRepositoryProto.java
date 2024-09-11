package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicPaymentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicPaymentRepositoryProto extends JpaRepository<PublicPaymentEntity, java.util.UUID>, JpaSpecificationExecutor<PublicPaymentEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
