package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicCategoryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicCategoryRepositoryProto extends JpaRepository<PublicCategoryEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCategoryEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
