package com.project.anywhere.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.repository.resultset.GetReviewResultSet;

import lombok.Getter;

@Getter
public class GetReviewResponseDto extends ResponseDto{

    private Integer reviewId;
    private String reviewContent;
    private String reviewWriter;
    private String reviewCreatedAt;
    private Integer reviewLikeCount;
    

    public GetReviewResponseDto(GetReviewResultSet resultSet){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewId = resultSet.getReviewId();
        this.reviewContent = resultSet.getReviewContent();
        this.reviewWriter = resultSet.getReviewWriter();
        this.reviewCreatedAt = resultSet.getReviewCreatedAt();
        this.reviewLikeCount = resultSet.getReviewLikeCount();
    }

    public static ResponseEntity<GetReviewResponseDto> success(GetReviewResultSet resultSet){
        GetReviewResponseDto responseBody = new GetReviewResponseDto(resultSet);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
