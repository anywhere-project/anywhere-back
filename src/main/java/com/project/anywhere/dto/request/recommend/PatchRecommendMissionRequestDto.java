package com.project.anywhere.dto.request.recommend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRecommendMissionRequestDto {

    private String missionName;
    private String missionContent;

}