package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class RecommendFood {
    
    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private String recommendCategory;
    private List<RecommendFoodEntity> foods;
    private List<RecommendImageEntity> images;

    private RecommendFood(RecommendPostEntity postEntity, List<RecommendFoodEntity> foodEntities, List<RecommendImageEntity> imageEntities) {
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.recommendCategory = postEntity.getRecommendCategory();
        this.foods = foodEntities;
        this.images = imageEntities;
    }

    public static List<RecommendFood> getList(List<RecommendPostEntity> postEntities, List<RecommendFoodEntity> foodEntities, List<RecommendImageEntity> imageEntities) {
        List<RecommendFood> recommendFoods = new ArrayList<>();

        for (RecommendPostEntity postEntity : postEntities) {
            Integer recommendId = postEntity.getRecommendId();

            List<RecommendFoodEntity> filteredFoods = new ArrayList<>();
            for (RecommendFoodEntity food : foodEntities) {
                if (food.getRecommendId().equals(recommendId)) {
                    filteredFoods.add(food);
                }
            }

            List<RecommendImageEntity> filteredImages = new ArrayList<>();
            for (RecommendImageEntity image : imageEntities) {
                if (image.getRecommendId().equals(recommendId)) {
                    filteredImages.add(image);
                }
            }

            RecommendFood recommendFood = new RecommendFood(postEntity, filteredFoods, filteredImages);
            recommendFoods.add(recommendFood);
        }

        return recommendFoods;
    }

}
