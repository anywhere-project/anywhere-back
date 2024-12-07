package com.project.anywhere.entity;

import com.project.anywhere.dto.request.recommend.PatchRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendAttractionRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recommend_attractions")
@Entity(name = "recommend_attractions")
public class RecommendAttractionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attractionId;
    private Integer recommendId;
    private String attractionName;
    private String attractionAddress;
    private String attractionContent;

    public RecommendAttractionEntity(PostRecommendAttractionRequestDto dto, Integer recommendId) {
        this.attractionName = dto.getAttractionName();
        this.attractionAddress = dto.getAttractionAddress();
        this.attractionContent = dto.getAttractionContent();
        this.recommendId = recommendId;
    }

    public void patch(PatchRecommendAttractionRequestDto dto, Integer attarctionId) {
        this.attractionName = dto.getAttractionName();
        this.attractionAddress = dto.getAttractionAddress();
        this.attractionContent = dto.getAttractionContent();
        this.attractionId = attarctionId;
    }

}
