package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendAttractionPostResponseDto extends ResponseDto {

    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private String recommendCategory;
    private List<RecommendAttractionEntity> attractions;
    private List<RecommendImageEntity> images;

    private GetRecommendAttractionPostResponseDto(RecommendPostEntity postEntity, List<RecommendAttractionEntity> attractionEntities, List<RecommendImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.recommendCategory = postEntity.getRecommendCategory();
        this.attractions = attractionEntities;
        this.images = imageEntities;
    }

    public static ResponseEntity<GetRecommendAttractionPostResponseDto> success(RecommendPostEntity postEntity, List<RecommendAttractionEntity> attractionEntities, List<RecommendImageEntity> imageEntities) {
        GetRecommendAttractionPostResponseDto responseBody = new GetRecommendAttractionPostResponseDto(postEntity, attractionEntities, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
