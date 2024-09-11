package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicRegionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicRegionRepositoryProto extends JpaRepository<PublicRegionEntity, java.util.UUID>, JpaSpecificationExecutor<PublicRegionEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
