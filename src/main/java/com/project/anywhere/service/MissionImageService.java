package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PostMissionImageRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

public interface MissionImageService {

    ResponseEntity<ResponseDto> postMissionImage(PostMissionImageRequestDto dto, Integer missionId, String userId);
    ResponseEntity<ResponseDto> deleteMissionImage(Integer imageId, Integer missionId, String userId);
    
}
