package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.service.ReviewLikeService;
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

    @PostMapping(value = { "", "/" })
    public ResponseEntity<ResponseDto> postReview(
        @RequestBody @Valid PostReviewRequestDto dto,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = reviewService.postReview(dto,  userId);
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

}
