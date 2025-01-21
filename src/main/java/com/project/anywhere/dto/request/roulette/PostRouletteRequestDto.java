package com.project.anywhere.dto.request.roulette;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRouletteRequestDto {
    
    @NotBlank
    private String areaName;
    @NotBlank
    private String foodName;
    @NotBlank
    private String attractionName;
    @NotBlank
    private String missionName;

}
