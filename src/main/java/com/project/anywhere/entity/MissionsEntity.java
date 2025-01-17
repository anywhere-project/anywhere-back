package com.project.anywhere.entity;

import com.project.anywhere.dto.request.roulette.PostMissionRequestDto;

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
@Table(name = "missions")
@Entity(name = "missions")
public class MissionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer missionId;
    private String missionName;
    private String missionContent;

    public MissionsEntity(PostMissionRequestDto dto) {
        this.missionName = dto.getMissionName();
        this.missionContent = dto.getMissionContent();
    }
}
