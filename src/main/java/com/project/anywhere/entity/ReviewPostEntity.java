package com.project.anywhere.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class ReviewPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    private String reviewContent;
    private String reviewWriter;
    private String reviewCreatedAt;
    private Integer reviewLikeCount = 0;

    public ReviewPostEntity(PostReviewRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
        this.reviewCreatedAt = simpleDateFormat.format(now);
        this.reviewContent = dto.getReviewContent();
    }
    
}
