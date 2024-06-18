package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicOrderDiscountEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicOrderDiscountRepository")
public interface PublicOrderDiscountRepository extends JpaRepository<PublicOrderDiscountEntity, java.util.UUID>, JpaSpecificationExecutor<PublicOrderDiscountEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
