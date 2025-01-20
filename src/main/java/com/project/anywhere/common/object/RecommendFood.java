package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.FoodImageEntity;
import com.project.anywhere.entity.FoodLikeEntity;
import com.project.anywhere.entity.RecommendFoodEntity;

import lombok.Getter;

@Getter
public class RecommendFood {

    private Integer foodId;
    private Integer recommendId;
    private String foodName;
    private String foodAddress;
    private String foodContent;
    private List<FoodImageEntity> images;
    private List<String> likeList;

    public RecommendFood(RecommendFoodEntity foodEntity, List<FoodImageEntity> images, List<String> likeList) {
        this.foodId = foodEntity.getFoodId();
        this.recommendId = foodEntity.getRecommendId();
        this.foodName = foodEntity.getFoodName();
        this.foodContent = foodEntity.getFoodContent();
        this.images = images;
        this.likeList = likeList;
    }

    public static List<RecommendFood> getList(List<RecommendFoodEntity> foodEntities, List<FoodImageEntity> foodImageEntities, List<FoodLikeEntity> foodLikeEntities) {
        List<RecommendFood> recommendFoods = new ArrayList<>();

        for (RecommendFoodEntity foodEntity : foodEntities) {
            List<FoodImageEntity> filteredImages = new ArrayList<>();

            for (FoodImageEntity foodImageEntity : foodImageEntities) {
                if (foodImageEntity.getFoodId().equals(foodEntity.getFoodId())) {
                    filteredImages.add(foodImageEntity);
                }
            }

            List<String> filteredUserList = new ArrayList<>();
            for (FoodLikeEntity foodLikeEntity : foodLikeEntities) {
                if (foodLikeEntity.getFoodId().equals(foodEntity.getFoodId())) {
                    filteredUserList.add(foodLikeEntity.getUserId());
                }
            }

            RecommendFood recommendFood = new RecommendFood(foodEntity, filteredImages, filteredUserList);
            recommendFoods.add(recommendFood);
        }

        return recommendFoods;
    }
}
