package com.example.service.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.profile.entity.PublicAgentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceProfile.publicAgentRepository")
public interface PublicAgentRepository extends JpaRepository<PublicAgentEntity, java.util.UUID>, JpaSpecificationExecutor<PublicAgentEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
