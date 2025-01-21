package com.project.anywhere.entity;

import com.project.anywhere.dto.request.roulette.PostRouletteRequestDto;

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
@Table(name = "my_randoms")
@Entity(name = "my_randoms")
public class MyRandomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer randomId;
    private String userId;
    private String areaName;
    private String foodName;
    private String attractionName;
    private String missionName;

    public MyRandomEntity(PostRouletteRequestDto dto) {
        this.areaName = dto.getAreaName();
        this.foodName = dto.getFoodName();
        this.attractionName = dto.getAttractionName();
        this.missionName = dto.getMissionName();
    }
    
}
