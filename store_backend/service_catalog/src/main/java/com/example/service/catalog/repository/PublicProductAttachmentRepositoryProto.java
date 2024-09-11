package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicProductAttachmentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicProductAttachmentRepositoryProto extends JpaRepository<PublicProductAttachmentEntity, java.util.UUID>, JpaSpecificationExecutor<PublicProductAttachmentEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
