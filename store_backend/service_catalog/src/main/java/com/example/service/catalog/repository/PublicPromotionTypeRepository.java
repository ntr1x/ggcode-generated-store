package com.example.service.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service.catalog.entity.PublicPromotionTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("serviceCatalog.publicPromotionTypeRepository")
public interface PublicPromotionTypeRepository extends JpaRepository<PublicPromotionTypeEntity, java.lang.Integer>, JpaSpecificationExecutor<PublicPromotionTypeEntity> {
  <T> Optional<T> findById(java.lang.Integer id, Class<T> type);
}
