package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PatchRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendMissionListResponseDto;

public interface RecommendMissionService {

    ResponseEntity<ResponseDto> postRecommendMission(PostRecommendMissionRequestDto dto, Integer recommendId, String userId);
    ResponseEntity<ResponseDto> patchRecommendMission(PatchRecommendMissionRequestDto dto, Integer recommendId, Integer missionId, String userId);
    ResponseEntity<ResponseDto> deleteRecommendMission(Integer recommendId, Integer missionId, String userId);
    ResponseEntity<? super GetRecommendMissionListResponseDto> getRecommendMissions(Integer recommendId);
}
