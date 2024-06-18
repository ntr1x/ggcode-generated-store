package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicSourceTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceCatalog.publicSourceTypeRepository")
public interface PublicSourceTypeRepository extends JpaRepository<PublicSourceTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicSourceTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
