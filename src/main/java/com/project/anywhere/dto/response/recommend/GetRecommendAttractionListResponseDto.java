package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendAttraction;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendAttractionListResponseDto extends ResponseDto {

    private List<RecommendAttraction> attractions;

    private GetRecommendAttractionListResponseDto(List<RecommendPostEntity> postEntities, List<RecommendAttractionEntity> attractionEntities, List<RecommendImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.attractions = RecommendAttraction.getList(postEntities, attractionEntities, imageEntities);
    }

    public static ResponseEntity<GetRecommendAttractionListResponseDto> success(List<RecommendPostEntity> postEntities, List<RecommendAttractionEntity> attractionEntities, List<RecommendImageEntity> imageEntities) {
        GetRecommendAttractionListResponseDto responseBody = new GetRecommendAttractionListResponseDto(postEntities, attractionEntities, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
