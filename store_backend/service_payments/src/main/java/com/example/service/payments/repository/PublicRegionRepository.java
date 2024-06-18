package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicRegionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicRegionRepository")
public interface PublicRegionRepository extends JpaRepository<PublicRegionEntity, java.util.UUID>, JpaSpecificationExecutor<PublicRegionEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
