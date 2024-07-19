package com.example.service.structure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.structure.entity.PublicGuideSubjectEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceStructure.publicGuideSubjectRepository")
public interface PublicGuideSubjectRepository extends JpaRepository<PublicGuideSubjectEntity, java.util.UUID>, JpaSpecificationExecutor<PublicGuideSubjectEntity> {
  <T> Optional<T> findById(java.util.UUID id, Class<T> type);
}
