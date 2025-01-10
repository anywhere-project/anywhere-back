package com.project.anywhere.entity;

import com.project.anywhere.dto.request.recommend.PatchRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recommend_foods")
@Entity(name = "recommend_foods")
public class RecommendFoodEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;
    private Integer recommendId;
    private String foodName;
    private String foodContent;
    private Integer foodLike = 0;

    public RecommendFoodEntity(PostRecommendFoodRequestDto dto, Integer recommendId) {
        this.foodName = dto.getFoodName();
        this.foodContent = dto.getFoodContent();
        this.recommendId = recommendId;
    }

    public void patch(PatchRecommendFoodRequestDto dto) {
        this.foodName = dto.getFoodName();
        this.foodContent = dto.getFoodContent();
    }

}
