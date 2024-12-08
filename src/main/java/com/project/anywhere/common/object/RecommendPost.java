package com.project.anywhere.common.object;

import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class RecommendPost {
    
    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private RecommendAttractionEntity attraction;
    private RecommendFoodEntity food;
    private RecommendMissionEntity mission;

    private RecommendPost(
        RecommendPostEntity postEntity, 
        RecommendAttractionEntity attraction, 
        RecommendFoodEntity food, 
        RecommendMissionEntity mission) 
    {
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.attraction = attraction;
        this.food = food;
        this.mission = mission;
    }

}
