package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.request.roulette.PostAreaRequestDto;
import com.project.anywhere.dto.request.roulette.PostAttractionRequestDto;
import com.project.anywhere.dto.request.roulette.PostFoodRequestDto;
import com.project.anywhere.dto.request.roulette.PostMissionRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.service.RouletteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roulette")
@RequiredArgsConstructor
public class RouletteController {

    private final RouletteService rouletteService;

    @PostMapping("/area") 
    ResponseEntity<ResponseDto> postArea(@RequestBody @Valid PostAreaRequestDto request, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.postArea(request, userId);
        return responseBody;
    }

    @DeleteMapping("/area/{areaId}")
    ResponseEntity<ResponseDto> deleteArea(@PathVariable("areaId") Integer areaId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.deleteArea(areaId, userId);
        return responseBody;
    }

    @PostMapping("/attraction/{areaId}")
    ResponseEntity<ResponseDto> postAttraction(@RequestBody @Valid PostAttractionRequestDto request, @PathVariable("areaId") Integer areaId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.postAttraction(request, areaId, userId);
        return responseBody;
    }

    @DeleteMapping("/attraction/{areaId}/{attractionId}")
    ResponseEntity<ResponseDto> deleteAttraction(@PathVariable("attractionId") Integer attractionId, @PathVariable("areaId") Integer areaId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.deleteAttraction(areaId, attractionId, userId);
        return responseBody;
    }

    @PostMapping("/food/{areaId}")
    public ResponseEntity<ResponseDto> postFood(@RequestBody @Valid PostFoodRequestDto request, @PathVariable("areaId") Integer areaId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.postFood(request, areaId, userId);
        return responseBody;
    }

    @DeleteMapping("/food/{areaId}/{foodId}")
    public ResponseEntity<ResponseDto> deleteFood(@PathVariable("foodId") Integer foodId, @PathVariable("areaId") Integer areaId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.deleteFood(foodId, areaId, userId);
        return responseBody;
    }

    @PostMapping("/mission")
    public ResponseEntity<ResponseDto> postMission(@RequestBody @Valid PostMissionRequestDto request, @PathVariable("areaId") Integer areaId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.postMission(request, userId);
        return responseBody;
    }

    @DeleteMapping("/mission/{missionId}")
    public ResponseEntity<ResponseDto> deleteMission(@PathVariable("missionId") Integer missionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.deleteMission(missionId, userId);
        return responseBody;
    }
    
}
