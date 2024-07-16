package com.example.service.structure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.structure.entity.PublicShopEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceStructure.publicShopRepository")
public interface PublicShopRepository extends JpaRepository<PublicShopEntity, java.util.UUID>, JpaSpecificationExecutor<PublicShopEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
