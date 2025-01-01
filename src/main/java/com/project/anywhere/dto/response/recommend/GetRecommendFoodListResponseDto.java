package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendFood;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.FoodImageEntity;
import com.project.anywhere.entity.RecommendFoodEntity;

import lombok.Getter;

@Getter
public class GetRecommendFoodListResponseDto extends ResponseDto  {
    
    private List<RecommendFood> foods;

    private GetRecommendFoodListResponseDto(List<RecommendFoodEntity> foodEntities, List<FoodImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.foods = RecommendFood.getList(foodEntities, imageEntities);
    }

    public static ResponseEntity<GetRecommendFoodListResponseDto> success(List<RecommendFoodEntity> foodEntities, List<FoodImageEntity> imageEntities) {
        GetRecommendFoodListResponseDto responseBody = new GetRecommendFoodListResponseDto(foodEntities, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
