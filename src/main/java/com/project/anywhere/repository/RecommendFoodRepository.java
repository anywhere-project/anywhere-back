package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.RecommendFoodEntity;

@Repository
public interface RecommendFoodRepository extends JpaRepository<RecommendFoodEntity, Integer> {

    boolean existsByRecommendId(Integer recommendId);
    RecommendFoodEntity findByFoodId(Integer foodId);
    RecommendFoodEntity findByRecommendId(Integer recommendId);
    void deleteByFoodId(Integer foodId);

}
