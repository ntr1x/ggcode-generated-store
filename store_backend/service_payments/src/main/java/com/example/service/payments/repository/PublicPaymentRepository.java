package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicPaymentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicPaymentRepository")
public interface PublicPaymentRepository extends JpaRepository<PublicPaymentEntity, java.util.UUID>, JpaSpecificationExecutor<PublicPaymentEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
