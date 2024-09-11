package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicPromotionImageEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicPromotionImageRepositoryProto extends JpaRepository<PublicPromotionImageEntity, java.util.UUID>, JpaSpecificationExecutor<PublicPromotionImageEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
