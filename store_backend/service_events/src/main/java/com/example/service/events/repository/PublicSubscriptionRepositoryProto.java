package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicSubscriptionRepositoryProto extends JpaRepository<PublicSubscriptionEntity, java.util.UUID>, JpaSpecificationExecutor<PublicSubscriptionEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
