package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class RecommendPost {
    
    private Integer recommendId;
    private String recommendWriter;
    private String recommendCreatedAt;
    private Integer recommendLikeCount;
    private String recommendCategory;

    public RecommendPost(RecommendPostEntity postEntity) {
        this.recommendId = postEntity.getRecommendId();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.recommendCategory = postEntity.getRecommendCategory();
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
