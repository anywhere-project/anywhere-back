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

import com.project.anywhere.dto.request.hashtag.PostHashTagRequestDto;
import com.project.anywhere.dto.request.review.PatchReviewRequestDto;
import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.hashtag.GetHashTagListResponseDto;
import com.project.anywhere.service.HashTagService;
import com.project.anywhere.service.ReviewLikeService;
import com.project.anywhere.dto.response.review.GetReviewListResponseDto;
import com.project.anywhere.service.ReviewPostService;
import com.project.anywhere.service.ReviewScrapService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewPostService reviewService;
    private final ReviewLikeService likeService;
    private final ReviewScrapService scrapService;
    private final HashTagService hashTagService;

    @PostMapping(value = { "", "/" })
    public ResponseEntity<ResponseDto> postReview(
        @RequestBody @Valid PostReviewRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = reviewService.postReview(requestBody,  userId);
        return response;
    }

    @GetMapping(value = { "", "/"})
    public ResponseEntity<? super GetReviewListResponseDto> getReviewList(){
        ResponseEntity<? super GetReviewListResponseDto> response = reviewService.getReviewList();
        return response;
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ResponseDto> patchReview(
        @PathVariable("reviewId") Integer reviewId,
        @AuthenticationPrincipal String userId,
        @RequestBody @Valid PatchReviewRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = reviewService.patchReview(reviewId, userId, requestBody);
        return response;
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ResponseDto> deleteReview(
        @PathVariable("reviewId") Integer reviewId,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = reviewService.deleteReview(reviewId, userId);
        return response;
    }

    @PostMapping("/{reviewId}/like")
    public ResponseEntity<ResponseDto> reviewLike(@AuthenticationPrincipal String userId, @PathVariable("reviewId") Integer reviewId) {
        ResponseEntity<ResponseDto> response = likeService.reviewLike(userId, reviewId);
        return response;
    }
    
    @PostMapping("/{reviewId}/scrap")
    public ResponseEntity<ResponseDto> reviewScrap(@AuthenticationPrincipal String userId, @PathVariable("reviewId") Integer reviewId) {
        ResponseEntity<ResponseDto> response = scrapService.reviewScrap(userId, reviewId);
        return response;
    }

    @PostMapping("/{reviewId}/hash-tag")
    public ResponseEntity<ResponseDto> postHashTag(@RequestBody @Valid PostHashTagRequestDto request, @PathVariable("reviewId") Integer reviewId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = hashTagService.postHashTag(request, reviewId, userId);
        return response;
    }

    @DeleteMapping("/{reviewId}/hash-tag/{tagId}")
    public ResponseEntity<ResponseDto> deleteHashTag(@PathVariable("reviewId") Integer reviewId, @PathVariable("tagId") Integer tagId, @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = hashTagService.deleteHashTag(reviewId, tagId, userId);
        return response;
    }

    @GetMapping("/hash-tag")
    public ResponseEntity<? super GetHashTagListResponseDto> getHashTags() {
        ResponseEntity<? super GetHashTagListResponseDto> response = hashTagService.getHashTags();
        return response;
    }

}
