package com.example.service.structure.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ServiceStructurePublicGuideEntity")
@Table(schema = "public", name = "guide")
public class PublicGuideEntity {
    
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    private java.util.UUID id;
    
    @Column(name = "subject_id", columnDefinition = "uuid")
    private java.util.UUID subjectId;
    
    @Column(name = "name", columnDefinition = "text")
    private java.lang.String name;
    
    @Column(name = "link", columnDefinition = "text")
    private java.lang.String link;
    
    @Column(name = "description", columnDefinition = "text")
    private java.lang.String description;
    
    @Column(name = "attachment", columnDefinition = "text")
    private java.lang.String attachment;
    
    @Column(name = "position")
    private java.lang.Integer position;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private com.example.service.structure.entity.PublicGuideSubjectEntity subject;
}
