package com.project.anywhere.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.project.anywhere.entity.pk.ReviewScrapPk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_scraps")
@Entity(name = "review_scraps")
@IdClass(ReviewScrapPk.class)
public class ReviewScrapEntity {
    
    @Id
    private String userId;
    @Id
    private Integer reviewId;
    private String createdAt;

    public ReviewScrapEntity(String userId, Integer reviewId) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
        this.createdAt = simpleDateFormat.format(now);
        this.userId = userId;
        this.reviewId = reviewId;
    }

}
