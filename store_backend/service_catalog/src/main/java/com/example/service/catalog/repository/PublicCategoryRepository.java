package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicCategoryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceCatalog.publicCategoryRepository")
public interface PublicCategoryRepository extends JpaRepository<PublicCategoryEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCategoryEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
