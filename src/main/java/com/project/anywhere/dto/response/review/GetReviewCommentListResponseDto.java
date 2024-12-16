package com.project.anywhere.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.ReviewComment;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.ReviewCommentEntity;

import lombok.Getter;

@Getter
public class GetReviewCommentListResponseDto extends ResponseDto{

    private List<ReviewComment> reviewComments;

    private GetReviewCommentListResponseDto(List<ReviewCommentEntity> reviewCommentEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewComments = ReviewComment.getReviewCommentList(reviewCommentEntities);
    }

    public static ResponseEntity<GetReviewCommentListResponseDto> success(List<ReviewCommentEntity> reviewCommentEntities) {
        GetReviewCommentListResponseDto responseBody = new GetReviewCommentListResponseDto(reviewCommentEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
