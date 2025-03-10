package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.request.roulette.PostAreaRequestDto;
import com.project.anywhere.dto.request.roulette.PostAttractionRequestDto;
import com.project.anywhere.dto.request.roulette.PostFoodRequestDto;
import com.project.anywhere.dto.request.roulette.PostMissionRequestDto;
import com.project.anywhere.dto.request.roulette.PostRouletteRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.roulette.GetRouletteListResponseDto;
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

    @PostMapping("/food")
    public ResponseEntity<ResponseDto> postFood(@RequestBody @Valid PostFoodRequestDto request, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.postFood(request, userId);
        return responseBody;
    }

    @DeleteMapping("/food/{foodId}")
    public ResponseEntity<ResponseDto> deleteFood(@PathVariable("foodId") Integer foodId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.deleteFood(foodId, userId);
        return responseBody;
    }

    @PostMapping("/mission")
    public ResponseEntity<ResponseDto> postMission(@RequestBody @Valid PostMissionRequestDto request,@AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.postMission(request, userId);
        return responseBody;
    }

    @DeleteMapping("/mission/{missionId}")
    public ResponseEntity<ResponseDto> deleteMission(@PathVariable("missionId") Integer missionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.deleteMission(missionId, userId);
        return responseBody;
    }

    @PostMapping("/my-random")
    public ResponseEntity<ResponseDto> postMyRandom(@RequestBody @Valid PostRouletteRequestDto request, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.postMyRandom(request, userId);
        return responseBody;
    }

    @DeleteMapping("/my-random/{randomId}")
    public ResponseEntity<ResponseDto> deleteMyRandom(@PathVariable("randomId") Integer randomId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> responseBody = rouletteService.deleteMyRandom(randomId, userId);
        return responseBody;
    }

    @GetMapping("/my-random")
    public ResponseEntity<? super GetRouletteListResponseDto> getMyRandomList(@AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetRouletteListResponseDto> responseBody = rouletteService.getMyRandomList(userId);
        return responseBody;
    }
    
}
