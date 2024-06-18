package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicFavoriteEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicFavoriteRepository")
public interface PublicFavoriteRepository extends JpaRepository<PublicFavoriteEntity, java.util.UUID>, JpaSpecificationExecutor<PublicFavoriteEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
