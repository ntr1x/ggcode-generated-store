package com.example.service.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.profile.entity.PublicCustomerVerifyEmailEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceProfile.publicCustomerVerifyEmailRepository")
public interface PublicCustomerVerifyEmailRepository extends JpaRepository<PublicCustomerVerifyEmailEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCustomerVerifyEmailEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
