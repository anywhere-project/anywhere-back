package com.project.anywhere.dto.request.recommend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRecommendAttractionRequestDto {
    
    private String attractionName;
    private String attractionContent;
    private String attractionAddress;

}
