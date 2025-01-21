package com.project.anywhere.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.ReviewImage;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.repository.resultset.GetImageResultSet;
import com.project.anywhere.repository.resultset.GetReviewResultSet;

import lombok.Getter;

@Getter
public class GetReviewResponseDto extends ResponseDto{

    private Integer reviewId;
    private String reviewContent;
    private String reviewWriter;
    private String reviewCreatedAt;
    private Integer reviewLikeCount;
    private List<ReviewImage> images;
    private List<String> hashTags;

    public GetReviewResponseDto(GetReviewResultSet resultSet, List<GetImageResultSet> resultImageSets, List<String> resultTagSets){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewId = resultSet.getReviewId();
        this.reviewContent = resultSet.getReviewContent();
        this.reviewWriter = resultSet.getReviewWriter();
        this.reviewCreatedAt = resultSet.getReviewCreatedAt();
        this.reviewLikeCount = resultSet.getReviewLikeCount();
        this.images = ReviewImage.getList(resultImageSets);
        this.hashTags = resultTagSets;
    }

    public static ResponseEntity<GetReviewResponseDto> success(GetReviewResultSet resultSet, List<GetImageResultSet> resultImageSets, List<String> resultTagSets){
        GetReviewResponseDto responseBody = new GetReviewResponseDto(resultSet, resultImageSets, resultTagSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
