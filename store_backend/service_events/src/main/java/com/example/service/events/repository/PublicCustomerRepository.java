package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicCustomerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicCustomerRepository")
public interface PublicCustomerRepository extends JpaRepository<PublicCustomerEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCustomerEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
