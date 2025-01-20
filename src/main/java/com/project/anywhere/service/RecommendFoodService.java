package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PatchRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendFoodListResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendFoodPostResponseDto;

public interface RecommendFoodService {

    ResponseEntity<ResponseDto> postRecommendFood(PostRecommendFoodRequestDto dto, Integer recommendId, String userId);
    ResponseEntity<ResponseDto> patchRecommendFood(PatchRecommendFoodRequestDto dto, Integer recommendId, Integer foodId, String userId);
    ResponseEntity<ResponseDto> deleteRecommendFood(Integer recommendId, Integer foodId, String userId);
    ResponseEntity<? super GetRecommendFoodPostResponseDto> getRecommendFood(Integer recommendId);
    ResponseEntity<? super GetRecommendFoodListResponseDto> getRecommendFoods();

}
