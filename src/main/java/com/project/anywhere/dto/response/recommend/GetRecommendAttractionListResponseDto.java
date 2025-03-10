package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendAttraction;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.AttractionImageEntity;
import com.project.anywhere.entity.AttractionLikeEntity;
import com.project.anywhere.entity.RecommendAttractionEntity;

import lombok.Getter;

@Getter
public class GetRecommendAttractionListResponseDto extends ResponseDto {
    
    private List<RecommendAttraction> attractions;

    private GetRecommendAttractionListResponseDto(List<RecommendAttractionEntity> attractionEntities, List<AttractionImageEntity> imageEntities, List<AttractionLikeEntity> attractionLikeEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.attractions = RecommendAttraction.getList(attractionEntities, imageEntities, attractionLikeEntities);
    }

    public static ResponseEntity<GetRecommendAttractionListResponseDto> success(List<RecommendAttractionEntity> attractionEntities, List<AttractionImageEntity> imageEntities, List<AttractionLikeEntity> attractionLikeEntities) {
        GetRecommendAttractionListResponseDto responseBody = new GetRecommendAttractionListResponseDto(attractionEntities, imageEntities, attractionLikeEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
