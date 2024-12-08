package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendAttractionEntity;

import lombok.Getter;

@Getter
public class RecommendAttraction {
    
    private Integer attractionId;
    private Integer recommendId;
    private String attractionName;
    private String attractionAddress;
    private String attractionContent;

    private RecommendAttraction(RecommendAttractionEntity attractionEntity) {
        this.attractionId = attractionEntity.getAttractionId();
        this.recommendId = attractionEntity.getRecommendId();
        this.attractionName = attractionEntity.getAttractionName();
        this.attractionAddress = attractionEntity.getAttractionAddress();
        this.attractionContent = attractionEntity.getAttractionContent();
    }

    public static List<RecommendAttraction> getList(List<RecommendAttractionEntity> attractionEntities, Integer recommendId) {
        List<RecommendAttraction> recommendAttractions = new ArrayList<>();
        for (RecommendAttractionEntity attractionEntity : attractionEntities) {
            if (attractionEntity.getRecommendId().equals(recommendId)) {
                RecommendAttraction recommendAttraction = new RecommendAttraction(attractionEntity);
                recommendAttractions.add(recommendAttraction);
            }
        }
        return recommendAttractions;
    }

}
