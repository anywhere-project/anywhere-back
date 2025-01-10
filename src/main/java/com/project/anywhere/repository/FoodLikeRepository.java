package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.FoodLikeEntity;
import com.project.anywhere.entity.pk.FoodLikePk;

import jakarta.transaction.Transactional;

@Repository
public interface FoodLikeRepository extends JpaRepository<FoodLikeEntity, FoodLikePk> {

    boolean existsByUserIdAndFoodId(String userId, Integer foodId);
    @Transactional
    void deleteByUserIdAndFoodId(String userId, Integer foodId);
    
}
