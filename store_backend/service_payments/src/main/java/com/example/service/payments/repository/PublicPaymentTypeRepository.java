package com.example.service.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.payments.entity.PublicPaymentTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("servicePayments.publicPaymentTypeRepository")
public interface PublicPaymentTypeRepository extends JpaRepository<PublicPaymentTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicPaymentTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
