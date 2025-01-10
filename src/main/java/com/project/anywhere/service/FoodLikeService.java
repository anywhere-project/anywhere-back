package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseDto;

public interface FoodLikeService {

    ResponseEntity<ResponseDto> foodLike(Integer foodId, String userId);
}
