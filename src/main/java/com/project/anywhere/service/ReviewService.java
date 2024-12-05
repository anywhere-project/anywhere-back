package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.request.reviewImages.PostReviewImagesRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

public interface ReviewService {

    ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto,PostReviewImagesRequestDto dto2, String userId);
    
}
