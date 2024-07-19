package com.example.service.structure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.structure.entity.PublicGuideEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceStructure.publicGuideRepository")
public interface PublicGuideRepository extends JpaRepository<PublicGuideEntity, java.util.UUID>, JpaSpecificationExecutor<PublicGuideEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
