package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.entity.ReviewImagesEntity;

import lombok.Getter;

@Getter
public class ReviewPost {
    private Integer reviewId;
    private String reviewContent;
    private String reviewWriter;
    private String reviewCreatedAt;
    private Integer reviewLikeCount;
    private List<ReviewImagesEntity> imageUrl;

    public ReviewPost(ReviewPostEntity reviewEntity, List<ReviewImagesEntity> imageUrl) {
        this.reviewId = reviewEntity.getReviewId();
        this.reviewContent = reviewEntity.getReviewContent();
        this.reviewWriter = reviewEntity.getReviewWriter();
        this.reviewCreatedAt = reviewEntity.getReviewCreatedAt();
        this.reviewLikeCount = reviewEntity.getReviewLikeCount();
        this.imageUrl = imageUrl;
        
    }

    public static List<ReviewPost> getList(List<ReviewPostEntity> reviewEntities, List<ReviewImagesEntity> reviewImageEntities) {
        List<ReviewPost> reviews = new ArrayList<>();
        
        for (ReviewPostEntity reviewEntity : reviewEntities) {
            List<ReviewImagesEntity> relatedImages = new ArrayList<>();
            
            for (ReviewImagesEntity imageEntity : reviewImageEntities) {
                if (imageEntity.getReviewId().equals(reviewEntity.getReviewId())) {
                    relatedImages.add(imageEntity);
                }
            }

            // Review 객체 생성 후 추가
            ReviewPost review = new ReviewPost(reviewEntity, relatedImages);
            reviews.add(review);
        }

        return reviews;
    }
        
}
