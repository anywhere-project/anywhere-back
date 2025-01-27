package com.project.anywhere.entity;

import com.project.anywhere.dto.request.roulette.PostAttractionRequestDto;

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
@Table(name = "attractions")
@Entity(name = "attractions")
public class AttractionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attractionId;
    private Integer areaId;
    private String attractionName;
    private String attractionAddress;

    public AttractionsEntity(Integer areaId, PostAttractionRequestDto dto) {
        this.areaId = areaId;
        this.attractionName = dto.getAttractionName();
        this.attractionAddress = dto.getAttractionAddress();
    }
}
