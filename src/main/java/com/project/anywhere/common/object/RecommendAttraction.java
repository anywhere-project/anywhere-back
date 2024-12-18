package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendAttractionEntity;

import lombok.Getter;

@Getter
public class RecommendAttraction {

    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private Integer attractionId;
    private String attractionName;
    private String attractionAddress;
    private String attractionContent;

    public RecommendAttraction(RecommendAttractionEntity attractionEntity) {
        this.attractionId = attractionEntity.getAttractionId();
        this.attractionName = attractionEntity.getAttractionName();
        this.attractionAddress = attractionEntity.getAttractionAddress();
        this.attractionContent = attractionEntity.getAttractionContent();
    }

    public static List<RecommendAttraction> getList(List<RecommendAttractionEntity> attractionEntities) {
        List<RecommendAttraction> attractions = new ArrayList<>();

        for (RecommendAttractionEntity attractionEntity: attractionEntities) {
            RecommendAttraction attraction = new RecommendAttraction(attractionEntity);
            attractions.add(attraction);
        }

        return attractions;
    }
    
}
