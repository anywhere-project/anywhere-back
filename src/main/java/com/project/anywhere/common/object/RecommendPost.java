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
        List<RecommendAttractionEntity> attractions,
        List<RecommendFoodEntity> foods,
        List<RecommendMissionEntity> missions,
        List<List<RecommendImageEntity>> images) {
        
        List<RecommendPost> recommendPosts = new ArrayList<>();

        for (int i = 0; i < postEntities.size(); i++) {
            RecommendPostEntity postEntity = postEntities.get(i);

            RecommendAttractionEntity attraction = attractions.get(i);
            RecommendFoodEntity food = foods.get(i);
            RecommendMissionEntity mission = missions.get(i);
            List<RecommendImageEntity> imageList = images.get(i);

            RecommendPost recommendPost = new RecommendPost(postEntity, attraction, food, mission, imageList);
            recommendPosts.add(recommendPost);
        }

        return recommendPosts;
    }
}
