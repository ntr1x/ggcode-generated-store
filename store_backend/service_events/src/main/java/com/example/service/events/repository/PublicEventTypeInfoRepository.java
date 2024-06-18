package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicEventTypeInfoEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicEventTypeInfoRepository")
public interface PublicEventTypeInfoRepository extends JpaRepository<PublicEventTypeInfoEntity, java.lang.String>, JpaSpecificationExecutor<PublicEventTypeInfoEntity> {
  <T> Optional<T> findByName(java.lang.String name, Class<T> type);
}
