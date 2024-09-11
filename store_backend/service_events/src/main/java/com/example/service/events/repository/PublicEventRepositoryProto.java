package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicEventEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicEventRepositoryProto extends JpaRepository<PublicEventEntity, java.util.UUID>, JpaSpecificationExecutor<PublicEventEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
