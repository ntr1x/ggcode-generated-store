package com.example.service.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.events.entity.PublicSubscriptionTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceEvents.publicSubscriptionTypeRepository")
public interface PublicSubscriptionTypeRepository extends JpaRepository<PublicSubscriptionTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicSubscriptionTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
