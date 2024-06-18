package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicEventTopicInfoEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicEventTopicInfoRepository")
public interface PublicEventTopicInfoRepository extends JpaRepository<PublicEventTopicInfoEntity, java.lang.String>, JpaSpecificationExecutor<PublicEventTopicInfoEntity> {
  <T> Optional<T> findByName(java.lang.String name, Class<T> type);
}
