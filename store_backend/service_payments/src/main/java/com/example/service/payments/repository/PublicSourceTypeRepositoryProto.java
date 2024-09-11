package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicSourceTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicSourceTypeRepositoryProto extends JpaRepository<PublicSourceTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicSourceTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
