package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicProductAttachmentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceCatalog.publicProductAttachmentRepository")
public interface PublicProductAttachmentRepository extends JpaRepository<PublicProductAttachmentEntity, java.util.UUID>, JpaSpecificationExecutor<PublicProductAttachmentEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
