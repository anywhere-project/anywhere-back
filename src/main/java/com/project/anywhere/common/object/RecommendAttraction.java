package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.AttractionImageEntity;
import com.project.anywhere.entity.AttractionLikeEntity;
import com.project.anywhere.entity.RecommendAttractionEntity;

import lombok.Getter;

@Getter
public class RecommendAttraction {

    private Integer attractionId;
    private Integer recommendId;
    private String attractionName;
    private String attractionAddress;
    private String attractionContent;
    private List<AttractionImageEntity> images;
    private List<String> likeList;

    public RecommendAttraction(RecommendAttractionEntity attractionEntity, List<AttractionImageEntity> images, List<String> likeList) {
        this.attractionId = attractionEntity.getAttractionId();
        this.recommendId = attractionEntity.getRecommendId();
        this.attractionName = attractionEntity.getAttractionName();
        this.attractionAddress = attractionEntity.getAttractionAddress();
        this.attractionContent = attractionEntity.getAttractionContent();
        this.images = images;
        this.likeList = likeList;
    }

    public static List<RecommendAttraction> getList(List<RecommendAttractionEntity> attractionEntities, List<AttractionImageEntity> imageEntities, List<AttractionLikeEntity> attractionLikeEntities) {
        List<RecommendAttraction> recommendAttractions = new ArrayList<>();

        for (RecommendAttractionEntity attractionEntity : attractionEntities) {
            List<AttractionImageEntity> filteredImages = new ArrayList<>(); 

            for (AttractionImageEntity imageEntity : imageEntities) {
                if (imageEntity.getAttractionId().equals(attractionEntity.getAttractionId())) {
                    filteredImages.add(imageEntity);
                }
            }

            List<String> filteredUserList = new ArrayList<>();
            for (AttractionLikeEntity likeEntity : attractionLikeEntities) {
                if (likeEntity.getAttractionId().equals(attractionEntity.getAttractionId())) {
                    filteredUserList.add(likeEntity.getUserId());
                }
            }

            RecommendAttraction recommendAttraction = new RecommendAttraction(attractionEntity, filteredImages, filteredUserList);
            recommendAttractions.add(recommendAttraction);
        }

        return recommendAttractions;
    }
}
