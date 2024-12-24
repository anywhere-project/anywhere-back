package com.project.anywhere.dto.request.recommend;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRecommendPostRequestDto {

    @Valid
    private List<PostRecommendFoodRequestDto> foods;
    @Valid
    private List<PostRecommendAttractionRequestDto> attractions;
    @Valid
    private List<PostRecommendMissionRequestDto> missions;
    @Valid
    private List<PostRecommendImageRequestDto> images;
    
}
