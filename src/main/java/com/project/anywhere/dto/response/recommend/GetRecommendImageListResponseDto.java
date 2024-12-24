package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendImage;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendImageEntity;

import lombok.Getter;

@Getter
public class GetRecommendImageListResponseDto extends ResponseDto {

    private List<RecommendImage> images;

    private GetRecommendImageListResponseDto(List<RecommendImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.images = RecommendImage.getList(imageEntities);
    }

    public static ResponseEntity<GetRecommendImageListResponseDto> success(List<RecommendImageEntity> imageEntities) {
        GetRecommendImageListResponseDto responseBody = new GetRecommendImageListResponseDto(imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
