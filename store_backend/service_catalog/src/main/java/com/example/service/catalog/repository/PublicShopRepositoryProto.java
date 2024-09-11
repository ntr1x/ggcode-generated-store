package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicShopEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicShopRepositoryProto extends JpaRepository<PublicShopEntity, java.util.UUID>, JpaSpecificationExecutor<PublicShopEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
