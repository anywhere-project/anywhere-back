package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.request.recommend.PatchRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendImageRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendPostRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendImageRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendPostListResponseDto;
import com.project.anywhere.service.RecommendAttractionService;
import com.project.anywhere.service.RecommendFoodService;
import com.project.anywhere.service.RecommendImageService;
import com.project.anywhere.service.RecommendLikeService;
import com.project.anywhere.service.RecommendMissionService;
import com.project.anywhere.service.RecommendPostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/recommend")
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendLikeService likeService;
    private final RecommendFoodService foodService;
    private final RecommendImageService imageService;
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

    @PostMapping("/{recommendId}/attraction")
    public ResponseEntity<ResponseDto> postRecommendAttraction(
            @RequestBody @Valid PostRecommendAttractionRequestDto request,
            @PathVariable("recommendId") Integer recommendId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionService.postRecommendAttraction(request, recommendId, userId);
        return response;
    }

    @PatchMapping("/{recommendId}/attraction/{attractionId}")
    public ResponseEntity<ResponseDto> patchRecommendAttraction(
            @RequestBody @Valid PatchRecommendAttractionRequestDto request,
            @PathVariable("recommendId") Integer recommendId,
            @PathVariable("attractionId") Integer attractionId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionService.patchRecommendAttraction(request, recommendId, attractionId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}/attraction/{attractionId}")
    public ResponseEntity<ResponseDto> deleteRecommendAttraction(
            @PathVariable("recommendId") Integer recommendId,
            @PathVariable("attractionId") Integer attractionId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionService.deleteRecommendAttraction(recommendId, attractionId, userId);
        return response;
    }
    
    @PostMapping("/{recommendId}/food")
    public ResponseEntity<ResponseDto> postRecommendFood(
            @RequestBody @Valid PostRecommendFoodRequestDto request,
            @PathVariable("recommendId") Integer recommendId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodService.postRecommendFood(request, recommendId, userId);
        return response;
    }

    @PatchMapping("/{recommendId}/food/{foodId}")
    public ResponseEntity<ResponseDto> patchRecommendFood(
            @RequestBody @Valid PatchRecommendFoodRequestDto request,
            @PathVariable("recommendId") Integer recommendId,
            @PathVariable("foodId") Integer foodId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodService.patchRecommendFood(request, recommendId, foodId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}/food/{foodId}")
    public ResponseEntity<ResponseDto> deleteRecommendFood(
            @PathVariable("recommendId") Integer recommendId,
            @PathVariable("foodId") Integer foodId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodService.deleteRecommendFood(recommendId, foodId, userId);
        return response;
    }

    @PostMapping("/{recommendId}/mission")
    public ResponseEntity<ResponseDto> postRecommendMission(
            @RequestBody @Valid PostRecommendMissionRequestDto request,
            @PathVariable("recommendId") Integer recommendId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionService.postRecommendMission(request, recommendId, userId);
        return response;
    }

    @PatchMapping("/{recommendId}/mission/{missionId}")
    public ResponseEntity<ResponseDto> patchRecommendMission(
            @RequestBody @Valid PatchRecommendMissionRequestDto request,
            @PathVariable("recommendId") Integer recommendId,
            @PathVariable("missionId") Integer missionId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionService.patchRecommendMission(request, recommendId, missionId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}/mission/{missionId}")
    public ResponseEntity<ResponseDto> deleteRecommendMission(
            @PathVariable("recommendId") Integer recommendId,
            @PathVariable("missionId") Integer missionId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionService.deleteRecommendMission(recommendId, missionId, userId);
        return response;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<? super GetRecommendPostListResponseDto> getRecommendPosts() {
        ResponseEntity<? super GetRecommendPostListResponseDto> response = recommendPostService.getRecommendPosts();
        return response;
    }

    @PostMapping("/{recommendId}/like")
    public ResponseEntity<ResponseDto> recommendLike(@AuthenticationPrincipal String userId, @PathVariable("recommendId") Integer recommendId) {
        ResponseEntity<ResponseDto> response = likeService.recommendLike(userId, recommendId);
        return response;
    }

    @PostMapping("/{recommendId}/image")
    public ResponseEntity<ResponseDto> postRecommendImage(
            @RequestBody @Valid PostRecommendImageRequestDto request,
            @PathVariable("recommendId") Integer recommendId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = imageService.postRecommendImage(request, recommendId, userId);
        return response;
    }

    @PatchMapping("/{recommendId}/image/{imageId}")
    public ResponseEntity<ResponseDto> patchRecommendImage(
            @RequestBody @Valid PatchRecommendImageRequestDto request,
            @PathVariable("recommendId") Integer recommendId,
            @PathVariable("imageId") Integer imageId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = imageService.patchRecommendImage(request, recommendId, imageId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}/image/{imageId}")
    public ResponseEntity<ResponseDto> deleteRecommendImage(
            @PathVariable("recommendId") Integer recommendId,
            @PathVariable("imageId") Integer imageId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = imageService.deleteRecommendImage(recommendId, imageId, userId);
        return response;
    }

}