package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicTemplateShapeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicTemplateShapeRepositoryProto extends JpaRepository<PublicTemplateShapeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicTemplateShapeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
