package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendPostResponseDto extends ResponseDto {

    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private RecommendAttractionEntity attraction;
    private RecommendFoodEntity food;
    private RecommendMissionEntity mission;
    private List<RecommendImageEntity> images;

    private GetRecommendPostResponseDto(RecommendPostEntity postEntity, RecommendAttractionEntity attractionEntity, RecommendFoodEntity foodEntity, RecommendMissionEntity missionEntity, List<RecommendImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.attraction = attractionEntity;
        this.food = foodEntity;
        this.mission = missionEntity;
        this.images = imageEntities;
    }

    public static ResponseEntity<GetRecommendPostResponseDto> success(RecommendPostEntity postEntity, RecommendAttractionEntity attractionEntity, RecommendFoodEntity foodEntity, RecommendMissionEntity missionEntity, List<RecommendImageEntity> imageEntities) {
        GetRecommendPostResponseDto responseBody = new GetRecommendPostResponseDto(postEntity, attractionEntity, foodEntity, missionEntity, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
