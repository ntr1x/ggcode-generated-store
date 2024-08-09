package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicPromotionTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicPromotionTypeRepositoryProto extends JpaRepository<PublicPromotionTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicPromotionTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
