package com.example.service.structure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.structure.entity.PublicGuideEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicGuideRepositoryProto extends JpaRepository<PublicGuideEntity, java.util.UUID>, JpaSpecificationExecutor<PublicGuideEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
