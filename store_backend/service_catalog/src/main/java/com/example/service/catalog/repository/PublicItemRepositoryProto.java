package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicItemEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicItemRepositoryProto extends JpaRepository<PublicItemEntity, java.util.UUID>, JpaSpecificationExecutor<PublicItemEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
