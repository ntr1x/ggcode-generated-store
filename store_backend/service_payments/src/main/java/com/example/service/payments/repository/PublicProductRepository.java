package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicProductEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicProductRepository")
public interface PublicProductRepository extends JpaRepository<PublicProductEntity, java.util.UUID>, JpaSpecificationExecutor<PublicProductEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
