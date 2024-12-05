package com.project.anywhere.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project.anywhere.dto.request.review.PostReviewRequestDto;

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
@Entity(name="review_posts")
@Table(name = "review_posts")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    private String reviewContent;
    private String reviewWriter;
    private String reviewCreatedAt;
    private Integer reviewLikeCount = 0;

    private static final DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void setReviewPostCreatedAt(){
        this.reviewCreatedAt = LocalDateTime.now().format(Formatter);
    }
    
    public ReviewEntity(PostReviewRequestDto dto){
        this.reviewId = dto.getReviewId();
        this.reviewContent = dto.getReviewContent();
        this.reviewWriter = dto.getReviewWriter();
        this.reviewCreatedAt = dto.getReviewCreatedAt();
        this.reviewLikeCount = dto.getReviewLikeCount();
    }


    
}
