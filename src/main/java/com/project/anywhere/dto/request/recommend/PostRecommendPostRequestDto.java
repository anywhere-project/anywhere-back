package com.project.anywhere.dto.request.recommend;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRecommendPostRequestDto {
    
    private List<PostRecommendFoodRequestDto> foods = new ArrayList<>();
    private List<PostRecommendAttractionRequestDto> attractions = new ArrayList<>();
    private List<PostRecommendMissionRequestDto> missions = new ArrayList<>();

}
