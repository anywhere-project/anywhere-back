package com.project.anywhere.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.common.object.Foods;
import com.project.anywhere.dto.response.food.GetFoodResponseDto;
import com.project.anywhere.entity.FoodEntity;
import com.project.anywhere.repository.FoodRepository;
import com.project.anywhere.service.FoodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImplement implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public ResponseEntity<? super GetFoodResponseDto> getFood() {

        List<FoodEntity> foodEntities = null;

        try {

            foodEntities = foodRepository.findAll();
            if (foodEntities == null || foodEntities.isEmpty())
                return GetFoodResponseDto.noExistFoodId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return GetFoodResponseDto.databaseError();
        }

        List<Foods> foods = foodEntities.stream()
                .map(foodEntity -> new Foods(foodEntity.getFoodId(), foodEntity.getFoodName()))
                .collect(Collectors.toList());

        return GetFoodResponseDto.success(foods);
    }

}
