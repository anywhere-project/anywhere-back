package com.project.anywhere.dto.request.recommend;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private List<String> images;
    
}

