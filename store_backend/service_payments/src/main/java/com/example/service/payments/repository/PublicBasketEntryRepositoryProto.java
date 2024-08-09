package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicBasketEntryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicBasketEntryRepositoryProto extends JpaRepository<PublicBasketEntryEntity, java.util.UUID>, JpaSpecificationExecutor<PublicBasketEntryEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
