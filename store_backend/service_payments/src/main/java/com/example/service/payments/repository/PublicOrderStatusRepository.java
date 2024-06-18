package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicOrderStatusEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicOrderStatusRepository")
public interface PublicOrderStatusRepository extends JpaRepository<PublicOrderStatusEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicOrderStatusEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
