package com.project.anywhere.entity;

import com.project.anywhere.dto.request.recommend.PatchRecommendImageRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendImageRequestDto;

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
@Table(name = "recommend_images")
@Entity(name = "recommend_images")
public class RecommendImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    private Integer recommendId;
    private String imageUrl;
    private Integer imageOrder = 0;

    public RecommendImageEntity(PostRecommendImageRequestDto dto, Integer recommendId) {
        this.imageUrl = dto.getImageUrl();
        this.imageOrder = dto.getImageOrder();
        this.recommendId = recommendId;
    }

    public void patch(PatchRecommendImageRequestDto dto) {
        this.imageUrl = dto.getImageUrl();
        this.imageOrder = dto.getImageOrder();
    }
    
}
