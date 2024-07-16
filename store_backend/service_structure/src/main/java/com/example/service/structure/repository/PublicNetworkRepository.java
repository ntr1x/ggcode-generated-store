package com.example.service.structure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.structure.entity.PublicNetworkEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceStructure.publicNetworkRepository")
public interface PublicNetworkRepository extends JpaRepository<PublicNetworkEntity, java.util.UUID>, JpaSpecificationExecutor<PublicNetworkEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
