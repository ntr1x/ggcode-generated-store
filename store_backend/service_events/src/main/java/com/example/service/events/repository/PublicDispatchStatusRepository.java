package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicDispatchStatusEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicDispatchStatusRepository")
public interface PublicDispatchStatusRepository extends JpaRepository<PublicDispatchStatusEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicDispatchStatusEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
