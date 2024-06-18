package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicSourceTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicSourceTypeRepository")
public interface PublicSourceTypeRepository extends JpaRepository<PublicSourceTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicSourceTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
