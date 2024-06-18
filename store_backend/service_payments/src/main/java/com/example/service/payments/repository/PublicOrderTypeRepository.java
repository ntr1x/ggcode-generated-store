package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicOrderTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicOrderTypeRepository")
public interface PublicOrderTypeRepository extends JpaRepository<PublicOrderTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicOrderTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
