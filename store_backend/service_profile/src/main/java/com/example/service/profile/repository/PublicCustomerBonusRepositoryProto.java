package com.example.service.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.profile.entity.PublicCustomerBonusEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PublicCustomerBonusRepositoryProto extends JpaRepository<PublicCustomerBonusEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCustomerBonusEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}