package com.example.gateway.events.repository;

import com.example.gateway.events.entity.PublicCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("gatewayEvents.publicCustomerRepository")
public interface PublicCustomerRepository extends JpaRepository<PublicCustomerEntity, java.util.UUID>, JpaSpecificationExecutor<PublicCustomerEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);

  @Query("SELECT c.phone FROM GatewayEventsPublicCustomerEntity c WHERE c.id = :id")
  String findPhoneById(@Param("id") UUID id);

  @Query("SELECT c.email FROM GatewayEventsPublicCustomerEntity c WHERE c.id = :id")
  String findEmailById(@Param("id") UUID id);

  @Modifying
  @Query("UPDATE GatewayEventsPublicCustomerEntity c SET c.phone = :phone WHERE c.id = :customerId")
  void updatePhoneNumber(@Param("customerId") UUID customerId, @Param("phone") String phone);
}
