package com.project.anywhere.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "recommend_posts")
@Entity(name = "recommend_posts")
public class RecommendPostEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private String recommendCategory;
    private Integer recommendLikeCount = 0;

    public RecommendPostEntity(PostRecommendPostRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
        this.recommendCreatedAt = simpleDateFormat.format(now);
        this.recommendCategory = dto.getRecommendCategory();
    }

    public void patch() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        this.recommendCreatedAt = simpleDateFormat.format(now);
    }

}
