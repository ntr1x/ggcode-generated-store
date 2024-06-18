package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicPromotionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceCatalog.publicPromotionRepository")
public interface PublicPromotionRepository extends JpaRepository<PublicPromotionEntity, java.util.UUID>, JpaSpecificationExecutor<PublicPromotionEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
