package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetRecommendPostListResponseDto extends ResponseDto {

    private List<GetRecommendPostResponseDto> recommendPosts;

    public GetRecommendPostListResponseDto(List<GetRecommendPostResponseDto> recommendPosts) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendPosts = recommendPosts;
    }

    public static ResponseEntity<GetRecommendPostListResponseDto> success(List<GetRecommendPostResponseDto> responseList) {
        GetRecommendPostListResponseDto responseBody = new GetRecommendPostListResponseDto(responseList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}

