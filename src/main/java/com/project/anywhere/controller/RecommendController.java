package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.request.recommend.PatchRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendPostRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.service.RecommendAttractionService;
import com.project.anywhere.service.RecommendFoodService;
import com.project.anywhere.service.RecommendMissionService;
import com.project.anywhere.service.RecommendPostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/recommend")
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendFoodService foodService;
    private final RecommendMissionService missionService;
    private final RecommendPostService recommendPostService;
    private final RecommendAttractionService attractionService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<ResponseDto> postRecommendPost(@RequestBody @Valid PostRecommendPostRequestDto request, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = recommendPostService.postRecommendPost(request, userId);
        return response;
    }

    @PatchMapping("/{recommendId}")
    public ResponseEntity<ResponseDto> patchRecommendPost(@RequestBody @Valid PatchRecommendPostRequestDto request, 
    @PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = recommendPostService.patchRecommendPost(request, recommendId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}")
    public ResponseEntity<ResponseDto> deleteRecommendPost(@PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = recommendPostService.deleteRecommendPost(recommendId, userId);
        return response;
    }

    @PostMapping("/attraction/{recommendId}")
    public ResponseEntity<ResponseDto> postRecommendAttraction(@RequestBody @Valid PostRecommendAttractionRequestDto requset,
    @PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionService.postRecommendAttraction(requset, recommendId, userId);
        return response;
    }

    @PatchMapping("/attraction/{recommendId}")
    public ResponseEntity<ResponseDto> patchRecommendAttraction(@RequestBody @Valid PatchRecommendAttractionRequestDto request, 
    @PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionService.patchRecommendAttraction(request, recommendId, userId);
        return response;
    }

    @DeleteMapping("/attraction/{recommendId}")
    public ResponseEntity<ResponseDto> deleteRecommendAttraction(@AuthenticationPrincipal String userId, @PathVariable("recommendId") Integer recommendId) {
        ResponseEntity<ResponseDto> response = attractionService.deleteRecommendAttraction(recommendId, userId);
        return response;
    }

    @PostMapping("/food/{recommendId}")
    public ResponseEntity<ResponseDto> postRecommendFood(@RequestBody @Valid PostRecommendFoodRequestDto request, @AuthenticationPrincipal String userId,
    @PathVariable("reommendId") Integer recommendId) {
        ResponseEntity<ResponseDto> response = foodService.postRecommendFood(request, recommendId, userId);
        return response;
    }

    @PatchMapping("/food/{recommendId}")
    public ResponseEntity<ResponseDto> patchRecommendFood(@RequestBody @Valid PatchRecommendFoodRequestDto request, @AuthenticationPrincipal String userId,
    @PathVariable("recommendId") Integer recommendId) {
        ResponseEntity<ResponseDto> response = foodService.patchRecommendFood(request, recommendId, userId);
        return response;
    }

    @DeleteMapping("/food/{recommendId}")
    public ResponseEntity<ResponseDto> deleteRecommendFood(@PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodService.deleteRecommendFood(recommendId, userId);
        return response;
    }

    @PostMapping("/mission/{recommendId}")
    public ResponseEntity<ResponseDto> postRecommendMission(@RequestBody @Valid PostRecommendMissionRequestDto request, @AuthenticationPrincipal String userId,
    @PathVariable("reommendId") Integer recommendId) {
        ResponseEntity<ResponseDto> response = missionService.postRecommendMission(request, recommendId, userId);
        return response;
    }

    @PatchMapping("/mission/{recommendId}")
    public ResponseEntity<ResponseDto> patchRecommendMission(@RequestBody @Valid PatchRecommendMissionRequestDto request, @AuthenticationPrincipal String userId,
    @PathVariable("recommendId") Integer recommendId) {
        ResponseEntity<ResponseDto> response = missionService.patchRecommendMission(request, recommendId, userId);
        return response;
    }

    @DeleteMapping("/mission/{recommendId}")
    public ResponseEntity<ResponseDto> deleteRecommendMission(@PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionService.deleteRecommendMission(recommendId, userId);
        return response;
    }
    
}
