package com.project.anywhere.dto.request.recommend;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRecommendMissionRequestDto {
    
    @NotBlank
    private String missionName;
    @NotBlank
    private String missionContent;
    @Valid
    private List<PostMissionImageRequestDto> images;

}
