package com.project.anywhere.dto.response.recommend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendMissionEntity;

import lombok.Getter;

@Getter
public class GetRecommendMissionResponseDto extends ResponseDto {
    
    private Integer missionId;
    private Integer recommendId;
    private String missionName;
    private String missionContent;

    public GetRecommendMissionResponseDto(RecommendMissionEntity missionEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.missionId = missionEntity.getMissionId();
        this.recommendId = missionEntity.getRecommendId();
        this.missionName = missionEntity.getMissionName();
        this.missionContent = missionEntity.getMissionContent();
    }

    public static ResponseEntity<GetRecommendMissionResponseDto> success(RecommendMissionEntity missionEntity) {
        GetRecommendMissionResponseDto responseBody = new GetRecommendMissionResponseDto(missionEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


}
