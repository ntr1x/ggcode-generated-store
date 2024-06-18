package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicPaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicPaymentStatusRepository")
public interface PublicPaymentStatusRepository extends JpaRepository<PublicPaymentStatusEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicPaymentStatusEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
