package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.roulette.PostAreaRequestDto;
import com.project.anywhere.dto.request.roulette.PostAttractionRequestDto;
import com.project.anywhere.dto.request.roulette.PostFoodRequestDto;
import com.project.anywhere.dto.request.roulette.PostMissionRequestDto;
import com.project.anywhere.dto.request.roulette.PostRouletteRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.roulette.GetRouletteListResponseDto;

public interface RouletteService {

    ResponseEntity<ResponseDto> postArea(PostAreaRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteArea(Integer areaId, String userId);
    ResponseEntity<ResponseDto> postAttraction(PostAttractionRequestDto dto, Integer areaId, String userId);
    ResponseEntity<ResponseDto> deleteAttraction(Integer areaId, Integer attractionId, String userId);
    ResponseEntity<ResponseDto> postFood(PostFoodRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteFood(Integer foodId, String userId);
    ResponseEntity<ResponseDto> postMission(PostMissionRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteMission(Integer missionId, String userId);
    ResponseEntity<ResponseDto> postMyRandom(PostRouletteRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteMyRandom(Integer randomId, String userId);
    ResponseEntity<? super GetRouletteListResponseDto> getMyRandomList(String userId);
    
}
