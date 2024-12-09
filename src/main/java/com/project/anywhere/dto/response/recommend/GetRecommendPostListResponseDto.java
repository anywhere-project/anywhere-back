package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendPost;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetRecommendPostListResponseDto extends ResponseDto {

    private List<RecommendPost> recommendPosts;

    public GetRecommendPostListResponseDto(List<RecommendPost> recommendPosts) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendPosts = recommendPosts;
    }

    public static ResponseEntity<GetRecommendPostListResponseDto> success(List<RecommendPost> recommendPosts) {
        GetRecommendPostListResponseDto responseBody = new GetRecommendPostListResponseDto(recommendPosts);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
