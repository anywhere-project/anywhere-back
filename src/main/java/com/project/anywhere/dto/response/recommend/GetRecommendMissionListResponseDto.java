package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendMission;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendMissionListResponseDto extends ResponseDto {

    private List<RecommendMission> missions;

    private GetRecommendMissionListResponseDto(List<RecommendPostEntity> postEntities, List<RecommendMissionEntity> missionEntities, List<RecommendImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.missions = RecommendMission.getList(postEntities, missionEntities, imageEntities);
    }

    public static ResponseEntity<GetRecommendMissionListResponseDto> success(List<RecommendPostEntity> postEntities, List<RecommendMissionEntity> missionEntities, List<RecommendImageEntity> imageEntities) {
        GetRecommendMissionListResponseDto responseBody = new GetRecommendMissionListResponseDto(postEntities, missionEntities, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
