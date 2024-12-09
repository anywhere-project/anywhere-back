package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseDto;

public interface RecommendLikeService {
    
    ResponseEntity<ResponseDto> recommendLike(String userId, Integer recommendId);
    
}
