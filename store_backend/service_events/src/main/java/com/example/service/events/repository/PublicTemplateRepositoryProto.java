package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicTemplateEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicTemplateRepositoryProto extends JpaRepository<PublicTemplateEntity, java.util.UUID>, JpaSpecificationExecutor<PublicTemplateEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
