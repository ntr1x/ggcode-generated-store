package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicOrderEntryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicOrderEntryRepository")
public interface PublicOrderEntryRepository extends JpaRepository<PublicOrderEntryEntity, java.util.UUID>, JpaSpecificationExecutor<PublicOrderEntryEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
