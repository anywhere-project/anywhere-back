package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.anywhere.entity.FoodEntity;


public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {
    
    FoodEntity findByFoodId(Integer foodId);
    void deleteByFoodId(Integer foodId);
    
}
