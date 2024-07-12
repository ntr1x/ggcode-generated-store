package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicTemplateEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicTemplateRepository")
public interface PublicTemplateRepository extends JpaRepository<PublicTemplateEntity, java.util.UUID>, JpaSpecificationExecutor<PublicTemplateEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
