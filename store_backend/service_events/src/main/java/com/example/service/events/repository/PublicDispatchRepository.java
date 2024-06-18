package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicDispatchEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicDispatchRepository")
public interface PublicDispatchRepository extends JpaRepository<PublicDispatchEntity, java.util.UUID>, JpaSpecificationExecutor<PublicDispatchEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
