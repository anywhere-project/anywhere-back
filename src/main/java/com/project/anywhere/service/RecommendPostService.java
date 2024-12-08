package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PatchRecommendPostRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendPostListResponseDto;

public interface RecommendPostService {
    
    ResponseEntity<ResponseDto> postRecommendPost(PostRecommendPostRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchRecommendPost(PatchRecommendPostRequestDto dto, Integer recommendId, String userId);
    ResponseEntity<ResponseDto> deleteRecommendPost(Integer recommendId, String userId);
    ResponseEntity<? super GetRecommendPostListResponseDto> getRecommendPosts();

}
