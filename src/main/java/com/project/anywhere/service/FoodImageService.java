package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PostFoodImageRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

public interface FoodImageService {

    ResponseEntity<ResponseDto> postFoodImage(PostFoodImageRequestDto dto, Integer foodId, String userId);
    ResponseEntity<ResponseDto> deleteFoodImage(Integer imageId, Integer foodId, String userId);
    
}
