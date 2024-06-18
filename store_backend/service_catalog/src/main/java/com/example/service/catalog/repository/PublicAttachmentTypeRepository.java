package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicAttachmentTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceCatalog.publicAttachmentTypeRepository")
public interface PublicAttachmentTypeRepository extends JpaRepository<PublicAttachmentTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicAttachmentTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
