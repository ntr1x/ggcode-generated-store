package com.example.service.structure.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("serviceStructure.publicGuideSubjectRepository")
public interface PublicGuideSubjectRepository extends PublicGuideSubjectRepositoryProto {
    @Query("""
        SELECT e
        FROM ServiceStructurePublicGuideSubjectEntity e
        ORDER BY e.position ASC, e.id ASC
    """)
    <T> List<T> findTree(Class<T> clazz);
}
