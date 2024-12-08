package com.project.anywhere.dto.request.recommend;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRecommendAttractionRequestDto {
    
    @NotBlank
    private String attractionName;
    @NotBlank
    private String attractionContent;
    @NotBlank
    private String attractionAddress;

}

