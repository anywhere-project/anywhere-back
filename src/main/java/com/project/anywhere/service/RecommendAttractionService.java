package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PatchRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendAttractionRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendAttractionListResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendAttractionPostResponseDto;

public interface RecommendAttractionService {

    ResponseEntity<ResponseDto> postRecommendAttraction(PostRecommendAttractionRequestDto dto, Integer recommendId, String userId);
    ResponseEntity<ResponseDto> patchRecommendAttraction(PatchRecommendAttractionRequestDto dto, Integer recommendId, Integer attractionId, String userId);
    ResponseEntity<ResponseDto> deleteRecommendAttraction(Integer recommendId, Integer attractionId, String userId);
    ResponseEntity<? super GetRecommendAttractionPostResponseDto> getRecommendAttraction(Integer recommendId);
    ResponseEntity<? super GetRecommendAttractionListResponseDto> getRecommendAttractions();

}
