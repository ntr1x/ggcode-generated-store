package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicTemplateShapeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicTemplateShapeRepository")
public interface PublicTemplateShapeRepository extends JpaRepository<PublicTemplateShapeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicTemplateShapeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
