package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.AttractionImageEntity;
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

    public RecommendAttraction(RecommendAttractionEntity attractionEntity, List<AttractionImageEntity> images) {
        this.attractionId = attractionEntity.getAttractionId();
        this.recommendId = attractionEntity.getRecommendId();
        this.attractionName = attractionEntity.getAttractionName();
        this.attractionAddress = attractionEntity.getAttractionAddress();
        this.attractionContent = attractionEntity.getAttractionContent();
        this.images = images;
    }

    public static List<RecommendAttraction> getList(List<RecommendAttractionEntity> attractionEntities, List<AttractionImageEntity> imageEntities) {
        List<RecommendAttraction> recommendAttractions = new ArrayList<>();

        for (RecommendAttractionEntity attractionEntity : attractionEntities) {
            List<AttractionImageEntity> filteredImages = new ArrayList<>(); 

            for (AttractionImageEntity imageEntity : imageEntities) {
                if (imageEntity.getAttractionId().equals(attractionEntity.getAttractionId())) {
                    filteredImages.add(imageEntity);
                }
            }

            RecommendAttraction recommendAttraction = new RecommendAttraction(attractionEntity, filteredImages);
            recommendAttractions.add(recommendAttraction);
        }

        return recommendAttractions;
    }
}
