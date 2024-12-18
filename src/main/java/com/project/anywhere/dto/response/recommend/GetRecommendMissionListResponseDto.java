package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendMission;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendMissionEntity;

import lombok.Getter;

@Getter
public class GetRecommendMissionListResponseDto extends ResponseDto {

    private List<RecommendMission> missions;

    private GetRecommendMissionListResponseDto(List<RecommendMissionEntity> missionEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.missions = RecommendMission.getList(missionEntities);
    }

    public static ResponseEntity<GetRecommendMissionListResponseDto> success(List<RecommendMissionEntity> missionEntities) {
        GetRecommendMissionListResponseDto responseBody = new GetRecommendMissionListResponseDto(missionEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
