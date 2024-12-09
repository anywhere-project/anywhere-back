package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendImageEntity;
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
    private List<RecommendImageEntity> images;

    public RecommendPost(
        RecommendPostEntity postEntity, 
        RecommendAttractionEntity attraction, 
        RecommendFoodEntity food, 
        RecommendMissionEntity mission,
        List<RecommendImageEntity> images) 
    {
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.attraction = attraction;
        this.food = food;
        this.mission = mission;
        this.images = images;
    }

    public static List<RecommendPost> getList(
        List<RecommendPostEntity> postEntities,
        RecommendAttractionEntity attraction, 
        RecommendFoodEntity food, 
        RecommendMissionEntity mission,
        List<RecommendImageEntity> images) {
        List<RecommendPost> recommendPosts = new ArrayList<>();
        for(RecommendPostEntity postEntity: postEntities) {
            RecommendPost recommendPost = new RecommendPost(postEntity, attraction, food, mission, images);
            recommendPosts.add(recommendPost);
        }

        return recommendPosts;
    }

}
