package com.example.gateway.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gateway.events.entity.PublicDispatchEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("gatewayEvents.publicDispatchRepository")
public interface PublicDispatchRepository extends JpaRepository<PublicDispatchEntity, java.util.UUID>, JpaSpecificationExecutor<PublicDispatchEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
