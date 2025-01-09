package com.project.anywhere.dto.response.recommend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendPostResponseDto extends ResponseDto {

    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private String recommendCategory;

    public GetRecommendPostResponseDto(RecommendPostEntity postEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.recommendCategory = postEntity.getRecommendCategory();
    }

    public static ResponseEntity<GetRecommendPostResponseDto> success(RecommendPostEntity postEntity) {
        GetRecommendPostResponseDto responseBody = new GetRecommendPostResponseDto(postEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
