package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendFoodEntity;

import lombok.Getter;

@Getter
public class RecommendFood {
    
    private Integer foodId;
    private String foodName;
    private String foodContent;

    private RecommendFood(RecommendFoodEntity foodEntity) {
        this.foodId = foodEntity.getFoodId();
        this.foodName = foodEntity.getFoodName();
        this.foodContent = foodEntity.getFoodContent();
    }

    public static List<RecommendFood> getList(List<RecommendFoodEntity> foodEntities) {
        List<RecommendFood> foods = new ArrayList<>();

        for (RecommendFoodEntity foodEntity: foodEntities) {
            RecommendFood food = new RecommendFood(foodEntity);
            foods.add(food);
        }
        
        return foods;
    }

}
