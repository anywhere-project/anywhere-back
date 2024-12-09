package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.request.review.PatchReviewRequestDto;
import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.review.GetReviewListResponseDto;
import com.project.anywhere.service.ReviewPostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewPostService reviewService;

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
    
}
