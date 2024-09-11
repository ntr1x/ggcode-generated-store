package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicDispatchStatusEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicDispatchStatusRepositoryProto extends JpaRepository<PublicDispatchStatusEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicDispatchStatusEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
