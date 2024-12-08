package com.project.anywhere.entity;

import com.project.anywhere.entity.pk.RecommendLikePk;

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
@Table(name = "recommend_likes")
@Entity(name = "recommend_likes")
@IdClass(RecommendLikePk.class)
public class RecommendLikeEntity {

    @Id
    private String userId;
    @Id
    private Integer recommendId;

    public RecommendLikeEntity(String userId, Integer recommendId) {
        this.userId = userId;
        this.recommendId = recommendId;
    }
    
}
