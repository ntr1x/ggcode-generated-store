package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicProductImageEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceCatalog.publicProductImageRepository")
public interface PublicProductImageRepository extends JpaRepository<PublicProductImageEntity, java.util.UUID>, JpaSpecificationExecutor<PublicProductImageEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
