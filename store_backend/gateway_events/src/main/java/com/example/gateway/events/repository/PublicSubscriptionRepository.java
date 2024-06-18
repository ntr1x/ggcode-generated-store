package com.example.gateway.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gateway.events.entity.PublicSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("gatewayEvents.publicSubscriptionRepository")
public interface PublicSubscriptionRepository extends JpaRepository<PublicSubscriptionEntity, java.util.UUID>, JpaSpecificationExecutor<PublicSubscriptionEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
