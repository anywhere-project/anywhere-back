package com.project.anywhere.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project.anywhere.dto.request.review.PostReviewCommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_comment")
@Entity(name = "review_comment")
public class ReviewCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewCommentId;
    private Integer reviewId;
    private String reviewCommentWriter;
    private String reviewCommentContent;
    private String reviewCommentCreatedAt;
    private Integer parentCommentId;
    private Boolean isDeleted = false;
    private Boolean isNextComment = false;  // 대댓글인지 유무
    private Integer depth = 0;
    private Integer orderNumber;

    private static final DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ReviewCommentEntity(PostReviewCommentRequestDto dto, String userId, Integer reviewId, Integer orderNumber) {
        this.reviewId = reviewId;
        this.reviewCommentWriter = userId;
        this.reviewCommentContent = dto.getReviewCommentContent();
        this.reviewCommentCreatedAt = LocalDateTime.now().format(Formatter);
        this.parentCommentId = dto.getParentCommentId();
        this.orderNumber = orderNumber + 1;
    }

    public ReviewCommentEntity(PostReviewCommentRequestDto dto, ReviewCommentEntity parentCommentEntity, String userId, Integer reviewId) {
        this.reviewId = reviewId;
        this.reviewCommentWriter = userId;
        this.reviewCommentContent = dto.getReviewCommentContent();
        this.reviewCommentCreatedAt = LocalDateTime.now().format(Formatter);
        this.parentCommentId = dto.getParentCommentId();
        this.isNextComment = true;
        this.depth = parentCommentEntity.getDepth() + 1;
        this.orderNumber = parentCommentEntity.getOrderNumber();
    }
    
}
