package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicBasketEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicBasketRepositoryProto extends JpaRepository<PublicBasketEntity, java.util.UUID>, JpaSpecificationExecutor<PublicBasketEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
