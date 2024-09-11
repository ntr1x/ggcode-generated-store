package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicImageTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicImageTypeRepositoryProto extends JpaRepository<PublicImageTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicImageTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
