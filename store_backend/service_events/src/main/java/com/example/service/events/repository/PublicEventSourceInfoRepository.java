package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicEventSourceInfoEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicEventSourceInfoRepository")
public interface PublicEventSourceInfoRepository extends JpaRepository<PublicEventSourceInfoEntity, java.lang.String>, JpaSpecificationExecutor<PublicEventSourceInfoEntity> {
  <T> Optional<T> findByName(java.lang.String name, Class<T> type);
}
