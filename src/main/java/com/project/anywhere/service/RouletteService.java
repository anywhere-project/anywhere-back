package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.roulette.PostAttractionRequestDto;
import com.project.anywhere.dto.request.roulette.PostFoodRequestDto;
import com.project.anywhere.dto.request.roulette.PostMissionRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

public interface RouletteService {

    ResponseEntity<ResponseDto> postAttraction(PostAttractionRequestDto dto, Integer areaId, String userId);
    ResponseEntity<ResponseDto> deleteAttraction(Integer areaId, Integer attractionId, String userId);
    ResponseEntity<ResponseDto> postFood(PostFoodRequestDto dto, Integer areaId, String userId);
    ResponseEntity<ResponseDto> deleteFood(Integer foodId, Integer areaId, String userId);
    ResponseEntity<ResponseDto> postMission(PostMissionRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteMission(Integer missionId, String userId);
    
}
