package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class RecommendAttraction {

    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private String recommendCategory;
    private List<RecommendAttractionEntity> attractions;
    private List<RecommendImageEntity> images;

    // Constructor
    public RecommendAttraction(RecommendPostEntity postEntity, List<RecommendAttractionEntity> attractionEntities, List<RecommendImageEntity> imageEntities) {
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.recommendCategory = postEntity.getRecommendCategory();
        this.attractions = attractionEntities;
        this.images = imageEntities;
    }

    public static List<RecommendAttraction> getList(List<RecommendPostEntity> postEntities, List<RecommendAttractionEntity> attractionEntities, List<RecommendImageEntity> imageEntities) {
        List<RecommendAttraction> recommendAttractions = new ArrayList<>();

        for (RecommendPostEntity postEntity : postEntities) {
            Integer recommendId = postEntity.getRecommendId();

            List<RecommendAttractionEntity> filteredAttractions = new ArrayList<>();
            for (RecommendAttractionEntity attraction : attractionEntities) {
                if (attraction.getRecommendId().equals(recommendId)) {
                    filteredAttractions.add(attraction);
                }
            }

            List<RecommendImageEntity> filteredImages = new ArrayList<>();
            for (RecommendImageEntity image : imageEntities) {
                if (image.getRecommendId().equals(recommendId)) {
                    filteredImages.add(image);
                }
            }

            RecommendAttraction recommendAttraction = new RecommendAttraction(postEntity, filteredAttractions, filteredImages);
            recommendAttractions.add(recommendAttraction);
        }

        return recommendAttractions;
    }
}
