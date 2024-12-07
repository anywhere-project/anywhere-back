package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

public interface ReviewPostService {

    ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, String userId);
    
}
