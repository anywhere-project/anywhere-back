package com.project.anywhere.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.ReviewPost;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.HashTagEntity;
import com.project.anywhere.entity.ReviewImagesEntity;
import com.project.anywhere.entity.ReviewLikeEntity;
import com.project.anywhere.entity.ReviewPostEntity;

import lombok.Getter;

@Getter
public class GetReviewListResponseDto extends ResponseDto{

    private List<ReviewPost> reviewPosts;

    private GetReviewListResponseDto(List<ReviewPostEntity> reviewPostEntities, List<ReviewImagesEntity> reviewImagesEntities, List<HashTagEntity> hashtags, List<ReviewLikeEntity> likes){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewPosts = ReviewPost.getList(reviewPostEntities,reviewImagesEntities, hashtags, likes);
    }

    public static ResponseEntity<GetReviewListResponseDto> success(List<ReviewPostEntity> reviewPostEntities, List<ReviewImagesEntity> reviewImagesEntities, List<HashTagEntity> hashTags, List<ReviewLikeEntity> likes){
        GetReviewListResponseDto responseBody = new GetReviewListResponseDto(reviewPostEntities, reviewImagesEntities, hashTags, likes);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
