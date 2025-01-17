package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendMission;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.MissionImageEntity;
import com.project.anywhere.entity.MissionLikeEntity;
import com.project.anywhere.entity.RecommendMissionEntity;

import lombok.Getter;

@Getter
public class GetRecommendMissionListResponseDto extends ResponseDto {

    private List<RecommendMission> missions;

    private GetRecommendMissionListResponseDto(List<RecommendMissionEntity> missionEntities, List<MissionImageEntity> imageEntities, List<MissionLikeEntity> missionLikeEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.missions = RecommendMission.getList(missionEntities, imageEntities, missionLikeEntities);
    }

    public static ResponseEntity<GetRecommendMissionListResponseDto> success(List<RecommendMissionEntity> missionEntities, List<MissionImageEntity> imageEntities, List<MissionLikeEntity> missionLikeEntities) {
        GetRecommendMissionListResponseDto responseBody = new GetRecommendMissionListResponseDto(missionEntities, imageEntities, missionLikeEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
