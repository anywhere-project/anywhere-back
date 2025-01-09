package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.response.food.GetFoodResponseDto;
import com.project.anywhere.service.FoodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food")
public class FoodController {

    private final FoodService foodService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<? super GetFoodResponseDto> getFood() {
        ResponseEntity<? super GetFoodResponseDto> response = foodService.getFood();
        return response;
    }
}