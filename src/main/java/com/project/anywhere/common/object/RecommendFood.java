package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendFoodEntity;

import lombok.Getter;

@Getter
public class RecommendFood {
    
    private Integer foodId;
    private Integer recommendId;
    private String foodName;
    private String foodContent;

    private RecommendFood(RecommendFoodEntity foodEntity) {
        this.foodId = foodEntity.getFoodId();
        this.recommendId = foodEntity.getRecommendId();
        this.foodName = foodEntity.getFoodName();
        this.foodContent = foodEntity.getFoodContent();
    }

    public static List<RecommendFood> getList(List<RecommendFoodEntity> foodEntities, Integer recommendId) {
        List<RecommendFood> recommendFoods = new ArrayList<>();
        for (RecommendFoodEntity foodEntity : foodEntities) {
            if (foodEntity.getRecommendId().equals(recommendId)) {
                RecommendFood recommendFood = new RecommendFood(foodEntity);
                recommendFoods.add(recommendFood);
            }
        }
        return recommendFoods;
    }

}
