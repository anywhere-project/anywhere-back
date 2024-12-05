package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.RecommendFoodEntity;

@Repository
public interface RecommendAttractionRepository extends JpaRepository<RecommendFoodEntity, Integer> {
    
}
