package com.project.anywhere.dto.request.recommend;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRecommendPostRequestDto {

    private PatchRecommendFoodRequestDto food;
    private PatchRecommendAttractionRequestDto attraction;
    private PatchRecommendMissionRequestDto mission;
    private List<PatchRecommendImageRequestDto> images;

}
