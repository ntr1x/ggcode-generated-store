package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicTemplateTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicTemplateTypeRepository")
public interface PublicTemplateTypeRepository extends JpaRepository<PublicTemplateTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicTemplateTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
