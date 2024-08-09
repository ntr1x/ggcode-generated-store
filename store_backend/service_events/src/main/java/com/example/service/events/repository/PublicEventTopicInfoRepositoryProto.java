package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicEventTopicInfoEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicEventTopicInfoRepositoryProto extends JpaRepository<PublicEventTopicInfoEntity, java.lang.String>, JpaSpecificationExecutor<PublicEventTopicInfoEntity> {
  <T> Optional<T> findByName(java.lang.String name, Class<T> type);
}
