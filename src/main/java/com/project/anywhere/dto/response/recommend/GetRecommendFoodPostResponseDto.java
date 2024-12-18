package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendFoodPostResponseDto extends ResponseDto {
    
    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private List<RecommendFoodEntity> foods;
    private List<RecommendImageEntity> images;

    public GetRecommendFoodPostResponseDto(RecommendPostEntity postEntity, List<RecommendFoodEntity> foodEntities, List<RecommendImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.foods = foodEntities;
        this.images = imageEntities;
    }

    public static ResponseEntity<GetRecommendFoodPostResponseDto> success(RecommendPostEntity postEntity, List<RecommendFoodEntity> foodEntities, List<RecommendImageEntity> imageEntities) {
        GetRecommendFoodPostResponseDto responseBody = new GetRecommendFoodPostResponseDto(postEntity, foodEntities, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
