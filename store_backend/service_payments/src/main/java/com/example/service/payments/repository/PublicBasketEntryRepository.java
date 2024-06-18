package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicBasketEntryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicBasketEntryRepository")
public interface PublicBasketEntryRepository extends JpaRepository<PublicBasketEntryEntity, java.util.UUID>, JpaSpecificationExecutor<PublicBasketEntryEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
