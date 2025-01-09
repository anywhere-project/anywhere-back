package com.project.anywhere.dto.response.food;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.Foods;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetFoodResponseDto extends ResponseDto {

    private List<Foods> foods;

    private GetFoodResponseDto(List<Foods> foods) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.foods = foods;
    }

    public static ResponseEntity<GetFoodResponseDto> success(List<Foods> foods) {
        GetFoodResponseDto responseBody = new GetFoodResponseDto(foods);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
