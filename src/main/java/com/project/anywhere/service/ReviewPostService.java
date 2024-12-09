package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.review.PatchReviewRequestDto;
import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.review.GetReviewListResponseDto;

public interface ReviewPostService {

    ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, String userId);

    ResponseEntity<? super GetReviewListResponseDto> getReviewList();
    
    ResponseEntity<ResponseDto> patchReview(Integer reviewId, String userId, PatchReviewRequestDto dto);
}
