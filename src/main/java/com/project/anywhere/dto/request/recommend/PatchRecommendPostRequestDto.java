package com.project.anywhere.dto.request.recommend;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRecommendPostRequestDto {
    
    private List<PatchRecommendFoodRequestDto> foods = new ArrayList<>();
    private List<PatchRecommendAttractionRequestDto> attractions = new ArrayList<>();
    private List<PatchRecommendMissionRequestDto> missions = new ArrayList<>();

}
