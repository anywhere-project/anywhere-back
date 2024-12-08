package com.project.anywhere.dto.request.recommend;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRecommendPostRequestDto {
    
    @Valid
    private PostRecommendFoodRequestDto food;
    @Valid
    private PostRecommendAttractionRequestDto attraction;
    @Valid
    private PostRecommendMissionRequestDto mission;
    @Valid
    private List<PostRecommendImageRequestDto> images;

}
