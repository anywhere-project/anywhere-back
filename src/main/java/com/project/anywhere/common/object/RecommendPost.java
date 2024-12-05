package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class RecommendPost {
    
    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;

    private RecommendPost(RecommendPostEntity postEntity) {
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
    }

    public static List<RecommendPost> getList(List<RecommendPostEntity> postEntities) {
        List<RecommendPost> recommendPosts = new ArrayList<>();
        for (RecommendPostEntity postEntity: postEntities) {
            RecommendPost recommendPost = new RecommendPost(postEntity);
            recommendPosts.add(recommendPost);
        }
        return recommendPosts;
    }

}
