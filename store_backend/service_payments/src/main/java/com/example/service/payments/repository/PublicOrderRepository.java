package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicOrderEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicOrderRepository")
public interface PublicOrderRepository extends JpaRepository<PublicOrderEntity, java.util.UUID>, JpaSpecificationExecutor<PublicOrderEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
