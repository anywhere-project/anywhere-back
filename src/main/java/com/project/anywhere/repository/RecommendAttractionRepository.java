package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.RecommendAttractionEntity;

@Repository
public interface RecommendAttractionRepository extends JpaRepository<RecommendAttractionEntity, Integer> {
    
    boolean existsByRecommendId(Integer recommendId);
    RecommendAttractionEntity findByAttractionId(Integer attractionId);
    List<RecommendAttractionEntity> findByRecommendId(Integer recommendId);
    void deleteByAttractionId(Integer attractionId);

}
