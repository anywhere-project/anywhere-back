package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

public interface RecommendPostService {
    
    ResponseEntity<ResponseDto> postRecommendPost(PostRecommendPostRequestDto request, String userId);

}
