package com.project.anywhere.entity;

import com.project.anywhere.entity.pk.ReviewLikePk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "review_likes")
@Entity(name = "review_likes")
@IdClass(ReviewLikePk.class)
public class ReviewLikeEntity {
    
    @Id
    private String userId;
    @Id
    private Integer reviewId;

    public ReviewLikeEntity(String userId, Integer reviewId) {
        this.userId = userId;
        this.reviewId = reviewId;
    }

}
