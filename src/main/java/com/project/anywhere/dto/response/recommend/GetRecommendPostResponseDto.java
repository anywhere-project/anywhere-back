package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendPostResponseDto {

    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private RecommendAttractionEntity attraction;
    private RecommendFoodEntity food;
    private RecommendMissionEntity mission;
    private List<RecommendImageEntity> images;

    public GetRecommendPostResponseDto(RecommendPostEntity recommendPostEntity, RecommendAttractionEntity attractionEntity, RecommendFoodEntity foodEntity, RecommendMissionEntity missionEntity, List<RecommendImageEntity> imageEntities) {
        this.recommendId = recommendPostEntity.getRecommendId();
        this.recommendCreatedAt = recommendPostEntity.getRecommendCreatedAt();
        this.recommendWriter = recommendPostEntity.getRecommendWriter();
        this.recommendLikeCount = recommendPostEntity.getRecommendLikeCount();
        this.attraction = attractionEntity;
        this.food = foodEntity;
        this.mission = missionEntity;
        this.images = imageEntities;
    }

    public static ResponseEntity<GetRecommendPostResponseDto> success(RecommendPostEntity recommendPostEntity, RecommendAttractionEntity attractionEntity, RecommendFoodEntity foodEntity, RecommendMissionEntity missionEntity, List<RecommendImageEntity> imageEntities) {
        GetRecommendPostResponseDto responseBody = new GetRecommendPostResponseDto(recommendPostEntity, attractionEntity, foodEntity, missionEntity, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
