package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicPromotionTargetEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicPromotionTargetRepositoryProto extends JpaRepository<PublicPromotionTargetEntity, java.util.UUID>, JpaSpecificationExecutor<PublicPromotionTargetEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}