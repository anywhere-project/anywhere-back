package com.project.anywhere.dto.request.roulette;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostAreaRequestDto {

    @NotBlank
    private String areaName;
    
}
