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
    private PatchRecommendFoodRequestDto food;
    @Valid
    private PatchRecommendAttractionRequestDto attraction;
    @Valid
    private PatchRecommendMissionRequestDto mission;
    @Valid
    private List<PatchRecommendImageRequestDto> images;

}
