package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicShopEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicShopRepository")
public interface PublicShopRepository extends JpaRepository<PublicShopEntity, java.util.UUID>, JpaSpecificationExecutor<PublicShopEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
