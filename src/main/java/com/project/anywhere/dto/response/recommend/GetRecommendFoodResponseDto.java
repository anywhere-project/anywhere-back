package com.project.anywhere.dto.response.recommend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendFoodEntity;

import lombok.Getter;

@Getter
public class GetRecommendFoodResponseDto extends ResponseDto {
    
    private Integer foodId;
    private Integer recommendId;
    private String foodName;
    private String foodContent;

    public GetRecommendFoodResponseDto(RecommendFoodEntity foodEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.foodId = foodEntity.getFoodId();
        this.recommendId = foodEntity.getRecommendId();
        this.foodName = foodEntity.getFoodName();
        this.foodContent = foodEntity.getFoodContent();
    }

    public static ResponseEntity<GetRecommendFoodResponseDto> success(RecommendFoodEntity foodEntity) {
        GetRecommendFoodResponseDto responseBody = new GetRecommendFoodResponseDto(foodEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
