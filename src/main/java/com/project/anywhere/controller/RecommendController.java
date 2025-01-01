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
import com.project.anywhere.dto.request.recommend.PatchRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendPostRequestDto;
import com.project.anywhere.dto.request.recommend.PostAttractionImageRequestDto;
import com.project.anywhere.dto.request.recommend.PostFoodImageRequestDto;
import com.project.anywhere.dto.request.recommend.PostMissionImageRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendAttractionListResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendAttractionPostResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendFoodListResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendFoodPostResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendMissionListResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendMissionPostResponseDto;
import com.project.anywhere.service.AttractionImageService;
import com.project.anywhere.service.FoodImageService;
import com.project.anywhere.service.MissionImageService;
import com.project.anywhere.service.RecommendAttractionService;
import com.project.anywhere.service.RecommendFoodService;
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
    private final RecommendMissionService missionService;
    private final RecommendPostService recommendPostService;
    private final RecommendAttractionService attractionService;
    private final FoodImageService foodImageService;
    private final MissionImageService missionImageService;
    private final AttractionImageService attractionImageService;

    @PostMapping(value = { "", "/" })
    public ResponseEntity<ResponseDto> postRecommendPost(@RequestBody @Valid PostRecommendPostRequestDto request, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = recommendPostService.postRecommendPost(request, userId);
        return response;
    }

    @PatchMapping("/{recommendId}/{category}")
    public ResponseEntity<ResponseDto> patchRecommendPost(@RequestBody @Valid PatchRecommendPostRequestDto request, @PathVariable("category") String category, @PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = recommendPostService.patchRecommendPost(request, category, recommendId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}")
    public ResponseEntity<ResponseDto> deleteRecommendPost(@PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = recommendPostService.deleteRecommendPost(recommendId, userId);
        return response;
    }

    @PostMapping("/{recommendId}/attraction")
    public ResponseEntity<ResponseDto> postRecommendAttraction(@RequestBody @Valid PostRecommendAttractionRequestDto request, @PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionService.postRecommendAttraction(request, recommendId, userId);
        return response;
    }

    @GetMapping("/attractions")
    public ResponseEntity<? super GetRecommendAttractionListResponseDto> getRecommendAttractionPosts() {
        ResponseEntity<? super GetRecommendAttractionListResponseDto> response = attractionService.getRecommendAttractionsPosts();
        return response;
    }

    @GetMapping("/{recommendId}/attractions")
    public ResponseEntity<? super GetRecommendAttractionPostResponseDto> getRecommendAttractionPost(@PathVariable("recommendId") Integer recommendId) {
        ResponseEntity<? super GetRecommendAttractionPostResponseDto> response = attractionService.getRecommendAttractionPost(recommendId);
        return response;
    }

    @PatchMapping("/{recommendId}/attraction/{attractionId}")
    public ResponseEntity<ResponseDto> patchRecommendAttraction(@RequestBody @Valid PatchRecommendAttractionRequestDto request, @PathVariable("recommendId") Integer recommendId, @PathVariable("attractionId") Integer attractionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionService.patchRecommendAttraction(request, recommendId, attractionId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}/attraction/{attractionId}")
    public ResponseEntity<ResponseDto> deleteRecommendAttraction(@PathVariable("recommendId") Integer recommendId, @PathVariable("attractionId") Integer attractionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionService.deleteRecommendAttraction(recommendId, attractionId, userId);
        return response;
    }

    @PostMapping("/{recommendId}/food")
    public ResponseEntity<ResponseDto> postRecommendFood(@RequestBody @Valid PostRecommendFoodRequestDto request, @PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodService.postRecommendFood(request, recommendId, userId);
        return response;
    }
    
    @GetMapping("/foods")
    public ResponseEntity<? super GetRecommendFoodListResponseDto> getRecommendFoodPosts() {
        ResponseEntity<? super GetRecommendFoodListResponseDto> response = foodService.getRecommendFoodPosts();
        return response;
    }

    @GetMapping("/{recommendId}/foods")
    public ResponseEntity<? super GetRecommendFoodPostResponseDto> getRecommendFoodPost(@PathVariable("recommendId") Integer recommendId) {
        ResponseEntity<? super GetRecommendFoodPostResponseDto> response = foodService.getRecommendFoodPost(recommendId);
        return response;
    }

    @PatchMapping("/{recommendId}/food/{foodId}")
    public ResponseEntity<ResponseDto> patchRecommendFood(@RequestBody @Valid PatchRecommendFoodRequestDto request, @PathVariable("recommendId") Integer recommendId, @PathVariable("foodId") Integer foodId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodService.patchRecommendFood(request, recommendId, foodId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}/food/{foodId}")
    public ResponseEntity<ResponseDto> deleteRecommendFood(@PathVariable("recommendId") Integer recommendId, @PathVariable("foodId") Integer foodId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodService.deleteRecommendFood(recommendId, foodId, userId);
        return response;
    }

    @PostMapping("/{recommendId}/mission")
    public ResponseEntity<ResponseDto> postRecommendMission(@RequestBody @Valid PostRecommendMissionRequestDto request, @PathVariable("recommendId") Integer recommendId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionService.postRecommendMission(request, recommendId, userId);
        return response;
    }

    @GetMapping("/missions")
    public ResponseEntity<? super GetRecommendMissionListResponseDto> getRecommendMissionPosts() {
        ResponseEntity<? super GetRecommendMissionListResponseDto> response = missionService.getRecommendMissionsPosts();
        return response;
    }

    @GetMapping("/{recommendId}/missions")
    public ResponseEntity<? super GetRecommendMissionPostResponseDto> getRecommendMissionPost(@PathVariable("recommendId") Integer recommendId) {
        ResponseEntity<? super GetRecommendMissionPostResponseDto> response = missionService.getRecommendMissionPost(recommendId);
        return response;
    }

    @PatchMapping("/{recommendId}/mission/{missionId}")
    public ResponseEntity<ResponseDto> patchRecommendMission(@RequestBody @Valid PatchRecommendMissionRequestDto request, @PathVariable("recommendId") Integer recommendId, @PathVariable("missionId") Integer missionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionService.patchRecommendMission(request, recommendId, missionId, userId);
        return response;
    }

    @DeleteMapping("/{recommendId}/mission/{missionId}")
    public ResponseEntity<ResponseDto> deleteRecommendMission(@PathVariable("recommendId") Integer recommendId, @PathVariable("missionId") Integer missionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionService.deleteRecommendMission(recommendId, missionId, userId);
        return response;
    }

    @PostMapping("/{recommendId}/like")
    public ResponseEntity<ResponseDto> recommendLike(@AuthenticationPrincipal String userId, @PathVariable("recommendId") Integer recommendId) {
        ResponseEntity<ResponseDto> response = likeService.recommendLike(userId, recommendId);
        return response;
    }

    @PostMapping("/attraction/{attractionId}")
    public ResponseEntity<ResponseDto> postAttractionImage(@Valid @RequestBody PostAttractionImageRequestDto request, @PathVariable("attractionId") Integer attractionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionImageService.postAttractionImage(request, attractionId, userId);
        return response;
    }

    @DeleteMapping("/attraction/{attractionId}/image/{imageId}")
    public ResponseEntity<ResponseDto> deleteAttractionImage(@PathVariable("imageId") Integer imageId, @PathVariable("attractionId") Integer attractionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = attractionImageService.deleteAttractionImage(imageId, attractionId, userId);
        return response;
    }

    @PostMapping("/food/{foodId}")
    public ResponseEntity<ResponseDto> postFoodImage(@Valid @RequestBody PostFoodImageRequestDto request, @PathVariable("foodId") Integer foodId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodImageService.postFoodImage(request, foodId, userId);
        return response;
    }

    @DeleteMapping("/food/{foodId}/image/{imageId}")
    public ResponseEntity<ResponseDto> deleteFoodImage(@PathVariable("imageId") Integer imageId, @PathVariable("foodId") Integer foodId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = foodImageService.deleteFoodImage(imageId, foodId, userId);
        return response;
    }

    @PostMapping("/mission/{missionId}")
    public ResponseEntity<ResponseDto> postAttractionImage(@Valid @RequestBody PostMissionImageRequestDto request, @PathVariable("missionId") Integer missionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionImageService.postMissionImage(request, missionId, userId);
        return response;
    }

    @DeleteMapping("/mission/{missionId}/image/{imageId}")
    public ResponseEntity<ResponseDto> deleteMissionImage(@PathVariable("imageId") Integer imageId, @PathVariable("missionId") Integer missionId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = missionImageService.deleteMissionImage(imageId, missionId, userId);
        return response;
    }

}