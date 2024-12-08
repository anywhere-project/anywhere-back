package com.project.anywhere.entity;

import com.project.anywhere.entity.pk.ReviewScrapPk;

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
@Table(name = "review_scraps")
@Entity(name = "review_scraps")
@IdClass(ReviewScrapPk.class)
public class ReviewScrapEntity {
    
    @Id
    private String userId;
    @Id
    private Integer reviewId;

    public ReviewScrapEntity(String userId, Integer reviewId) {
        this.userId = userId;
        this.reviewId = reviewId;
    }

}
