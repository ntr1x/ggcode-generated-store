package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicDispatchTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicDispatchTypeRepositoryProto extends JpaRepository<PublicDispatchTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicDispatchTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
