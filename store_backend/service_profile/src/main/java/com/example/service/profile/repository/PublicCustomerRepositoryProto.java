package com.example.service.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.profile.entity.PublicCustomerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicCustomerRepositoryProto extends JpaRepository<PublicCustomerEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCustomerEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
