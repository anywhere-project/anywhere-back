package com.project.anywhere.entity;

import com.project.anywhere.dto.request.recommend.PatchRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;

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
@Table(name = "recommend_missions")
@Entity(name = "recommend_missions")
public class RecommendMissionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer missionId;
    private Integer recommendId;
    private String missionName;
    private String missionContent;

    public RecommendMissionEntity(PostRecommendMissionRequestDto dto, Integer recommendId) {
        this.missionName = dto.getMissionName();
        this.missionContent = dto.getMissionContent();
        this.recommendId = recommendId;
    }

    public void patch(PatchRecommendMissionRequestDto dto, Integer missionId) {
        this.missionName = dto.getMissionName();
        this.missionContent = dto.getMissionContent();
        this.missionId = missionId;
    }

}
