package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendFood;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.FoodImageEntity;
import com.project.anywhere.entity.FoodLikeEntity;
import com.project.anywhere.entity.RecommendFoodEntity;

import lombok.Getter;

@Getter
public class GetRecommendFoodPostResponseDto extends ResponseDto {

    private List<RecommendFood> foods;

    private GetRecommendFoodPostResponseDto(List<RecommendFoodEntity> foodEntities, List<FoodImageEntity> imageEntities, List<FoodLikeEntity> userList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.foods = RecommendFood.getList(foodEntities, imageEntities, userList);
    }

    public static ResponseEntity<GetRecommendFoodPostResponseDto> success(List<RecommendFoodEntity> foodEntities, List<FoodImageEntity> imageEntities, List<FoodLikeEntity> userList) {
        GetRecommendFoodPostResponseDto responseBody = new GetRecommendFoodPostResponseDto(foodEntities, imageEntities, userList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
