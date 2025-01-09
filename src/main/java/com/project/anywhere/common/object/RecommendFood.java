package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.FoodImageEntity;
import com.project.anywhere.entity.RecommendFoodEntity;

import lombok.Getter;

@Getter
public class RecommendFood {

    private Integer foodId;
    private Integer recommendId;
    private String foodName;
    private String foodContent;
    private List<FoodImageEntity> images;

    public RecommendFood(RecommendFoodEntity foodEntity, List<FoodImageEntity> images) {
        this.foodId = foodEntity.getFoodId();
        this.recommendId = foodEntity.getRecommendId();
        this.foodName = foodEntity.getFoodName();
        this.foodContent = foodEntity.getFoodContent();
        this.images = images;
    }

    public static List<RecommendFood> getList(List<RecommendFoodEntity> foodEntities, List<FoodImageEntity> imageEntities) {
        List<RecommendFood> recommendFoods = new ArrayList<>();

        for (RecommendFoodEntity foodEntity : foodEntities) {
            List<FoodImageEntity> filteredImages = new ArrayList<>();

            for (FoodImageEntity imageEntity : imageEntities) {
                if (imageEntity.getFoodId().equals(foodEntity.getFoodId())) {
                    filteredImages.add(imageEntity);
                }
            }

            RecommendFood recommendFood = new RecommendFood(foodEntity, filteredImages);
            recommendFoods.add(recommendFood);
        }

        return recommendFoods;
    }
}
