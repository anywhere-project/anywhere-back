package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendAttraction;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.AttractionImageEntity;
import com.project.anywhere.entity.RecommendAttractionEntity;

import lombok.Getter;

@Getter
public class GetRecommendAttractionPostResponseDto extends ResponseDto {

    private List<RecommendAttraction> attractions;

    private GetRecommendAttractionPostResponseDto(List<RecommendAttractionEntity> attractionEntities, List<AttractionImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.attractions = RecommendAttraction.getList(attractionEntities, imageEntities);
    }

    public static ResponseEntity<GetRecommendAttractionPostResponseDto> success(List<RecommendAttractionEntity> attractionEntities, List<AttractionImageEntity> imageEntities) {
        GetRecommendAttractionPostResponseDto responseBody = new GetRecommendAttractionPostResponseDto(attractionEntities, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
