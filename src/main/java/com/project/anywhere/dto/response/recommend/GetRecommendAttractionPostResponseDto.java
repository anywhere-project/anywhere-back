package com.project.anywhere.dto.response.recommend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendAttractionEntity;

import lombok.Getter;

@Getter
public class GetRecommendAttractionPostResponseDto extends ResponseDto {

    private Integer attractionId;
    private Integer recommendId;
    private String attractionName;
    private String attractionAddress;
    private String attractionContent;

    private GetRecommendAttractionPostResponseDto(RecommendAttractionEntity attractionEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.attractionId = attractionEntity.getAttractionId();
        this.attractionName = attractionEntity.getAttractionName();
        this.attractionAddress = attractionEntity.getAttractionAddress();
        this.attractionContent = attractionEntity.getAttractionContent();
    }

    public static ResponseEntity<GetRecommendAttractionPostResponseDto> success(RecommendAttractionEntity attractionEntity) {
        GetRecommendAttractionPostResponseDto responseBody = new GetRecommendAttractionPostResponseDto(attractionEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
