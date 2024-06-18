package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicAgentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicAgentRepository")
public interface PublicAgentRepository extends JpaRepository<PublicAgentEntity, java.util.UUID>, JpaSpecificationExecutor<PublicAgentEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
