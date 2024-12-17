package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.ReviewCommentEntity;

import lombok.Getter;

@Getter
public class ReviewComment {

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

    private ReviewComment(ReviewCommentEntity reviewCommentEntity) {
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

    public static List<ReviewComment> getReviewCommentList(List<ReviewCommentEntity> reviewCommentEntities) {

        List<ReviewComment> reviewComments = new ArrayList<>();
        for(ReviewCommentEntity reviewCommentEntity: reviewCommentEntities) {
            ReviewComment reviewComment = new ReviewComment(reviewCommentEntity);
            reviewComments.add(reviewComment);
        }

        return reviewComments;

    }
    
}
