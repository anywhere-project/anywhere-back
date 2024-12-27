package com.project.anywhere.entity;

import com.project.anywhere.dto.request.recommend.PostMissionImageRequestDto;

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
@Table(name = "mission_images")
@Entity(name = "mission_images")
public class MissionImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    private Integer missionId;
    private String imageUrl;

    public MissionImageEntity(PostMissionImageRequestDto dto, Integer missionId) {
        this.imageUrl = dto.getImageUrl();
        this.missionId = missionId;
    }
    
}
