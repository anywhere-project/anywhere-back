package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.review.PatchReviewRequestDto;
import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.review.GetReviewListResponseDto;
import com.project.anywhere.dto.response.review.GetReviewResponseDto;

public interface ReviewPostService {

    ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, String userId);

    ResponseEntity<? super GetReviewResponseDto> getReview(Integer reviewId);

    ResponseEntity<? super GetReviewListResponseDto> getReviewList();
    
    ResponseEntity<ResponseDto> patchReview(Integer reviewId, String userId, PatchReviewRequestDto dto);

    ResponseEntity<ResponseDto> deleteReview(Integer reviewId, String userId);
}
