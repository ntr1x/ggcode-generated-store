package com.example.service.structure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.structure.entity.PublicNetworkTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceStructure.publicNetworkTypeRepository")
public interface PublicNetworkTypeRepository extends JpaRepository<PublicNetworkTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicNetworkTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
