package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendPost;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendPostListResponseDto extends ResponseDto {
    
    private List<RecommendPost> posts;

    private GetRecommendPostListResponseDto(List<RecommendPostEntity> postEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.posts = RecommendPost.getList(postEntities);
    }

    public static ResponseEntity<GetRecommendPostListResponseDto> success(List<RecommendPostEntity> postEntities) {
        GetRecommendPostListResponseDto responseBody = new GetRecommendPostListResponseDto(postEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
