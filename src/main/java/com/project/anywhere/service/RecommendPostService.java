package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendPostResponseDto;

public interface RecommendPostService {
    
    ResponseEntity<ResponseDto> postRecommendPost(PostRecommendPostRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchRecommendPost(Integer recommendId, String userId);
    ResponseEntity<ResponseDto> deleteRecommendPost(Integer recommendId, String userId);
    ResponseEntity<? super GetRecommendPostResponseDto> getRecommendPost(Integer recommendId);

}
