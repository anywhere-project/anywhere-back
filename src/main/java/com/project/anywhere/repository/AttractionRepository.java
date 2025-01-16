package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.anywhere.entity.AttractionsEntity;

public interface AttractionRepository extends JpaRepository<AttractionsEntity, Integer> {
    
    AttractionsEntity findByAttractionId(Integer attractionId);
    void deleteByAttractionIdAndAreaId(Integer attractionId, Integer araeId);

}
