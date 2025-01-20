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
public class GetRecommendMissionPostResponseDto extends ResponseDto {

    private List<RecommendMission> missions;

    private GetRecommendMissionPostResponseDto(List<RecommendMissionEntity> missionEntities, List<MissionImageEntity> imageEntities, List<MissionLikeEntity> userList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.missions = RecommendMission.getList(missionEntities, imageEntities, userList);
    }

    public static ResponseEntity<GetRecommendMissionPostResponseDto> success(List<RecommendMissionEntity> missionEntities, List<MissionImageEntity> imageEntities, List<MissionLikeEntity> userList) {
        GetRecommendMissionPostResponseDto responseBody = new GetRecommendMissionPostResponseDto(missionEntities, imageEntities, userList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
