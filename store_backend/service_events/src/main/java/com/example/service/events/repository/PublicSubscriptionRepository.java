package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicSubscriptionRepository")
public interface PublicSubscriptionRepository extends JpaRepository<PublicSubscriptionEntity, java.util.UUID>, JpaSpecificationExecutor<PublicSubscriptionEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
