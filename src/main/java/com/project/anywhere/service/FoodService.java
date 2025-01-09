package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.food.GetFoodResponseDto;

public interface FoodService {
    ResponseEntity<? super GetFoodResponseDto> getFood();
}
