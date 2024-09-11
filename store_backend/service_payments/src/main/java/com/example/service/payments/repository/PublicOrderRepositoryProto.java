package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicOrderEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicOrderRepositoryProto extends JpaRepository<PublicOrderEntity, java.util.UUID>, JpaSpecificationExecutor<PublicOrderEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
