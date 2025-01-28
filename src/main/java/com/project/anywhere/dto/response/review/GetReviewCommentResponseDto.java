package com.project.anywhere.dto.response.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.ReviewCommentEntity;

import lombok.Getter;

@Getter
public class GetReviewCommentResponseDto extends ResponseDto{

    private Integer reviewCommentId;
    private Integer reviewId;
    private String reviewCommentWriter;
    private String reviewCommentContent;
    private String reviewCommentCreatedAt;
    private Integer parentCommentId;
    private Boolean isDeleted;
    private Boolean isNextComment;
    private Integer depth;
    private Integer orderNumber;

    public GetReviewCommentResponseDto(ReviewCommentEntity reviewCommentEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewCommentId = reviewCommentEntity.getReviewCommentId();
        this.reviewId = reviewCommentEntity.getReviewId();
        this.reviewCommentWriter = reviewCommentEntity.getReviewCommentWriter();
        this.reviewCommentContent = reviewCommentEntity.getReviewCommentContent();
        this.reviewCommentCreatedAt = reviewCommentEntity.getReviewCommentCreatedAt();
        this.parentCommentId = reviewCommentEntity.getParentCommentId();
        this.isDeleted = reviewCommentEntity.getIsDeleted();
        this.isNextComment = reviewCommentEntity.getIsNextComment();
        this.depth = reviewCommentEntity.getDepth();
        this.orderNumber = reviewCommentEntity.getOrderNumber();
    }

    public static ResponseEntity<GetReviewCommentResponseDto> success(ReviewCommentEntity reviewCommentEntity){
        GetReviewCommentResponseDto responseBody = new GetReviewCommentResponseDto(reviewCommentEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
