package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseDto;

public interface ReviewLikeService {

    ResponseEntity<ResponseDto> reviewLike(String userId, Integer reviewId);
    
}
