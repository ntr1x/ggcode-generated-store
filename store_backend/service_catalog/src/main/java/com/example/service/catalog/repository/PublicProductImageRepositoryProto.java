package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicProductImageEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicProductImageRepositoryProto extends JpaRepository<PublicProductImageEntity, java.util.UUID>, JpaSpecificationExecutor<PublicProductImageEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
