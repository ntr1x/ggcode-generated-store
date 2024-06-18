package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicDispatchTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicDispatchTypeRepository")
public interface PublicDispatchTypeRepository extends JpaRepository<PublicDispatchTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicDispatchTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
