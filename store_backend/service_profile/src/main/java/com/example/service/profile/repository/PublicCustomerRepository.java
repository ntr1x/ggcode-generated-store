package com.example.service.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.profile.entity.PublicCustomerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceProfile.publicCustomerRepository")
public interface PublicCustomerRepository extends JpaRepository<PublicCustomerEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCustomerEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
