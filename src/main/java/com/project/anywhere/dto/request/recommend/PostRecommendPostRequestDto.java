package com.project.anywhere.dto.request.recommend;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRecommendPostRequestDto {
    private List<PostRecommendFoodRequestDto> foods;
    private List<PostRecommendAttractionRequestDto> attractions;
    private List<PostRecommendMissionRequestDto> missions;
}
