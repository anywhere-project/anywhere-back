package com.project.anywhere.dto.response.roulette;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.MyRandom;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.MyRandomEntity;

import lombok.Getter;

@Getter
public class GetRouletteListResponseDto extends ResponseDto {
    
    private List<MyRandom> myRandoms;

    private GetRouletteListResponseDto(List<MyRandomEntity> myRandomEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.myRandoms = MyRandom.getList(myRandomEntities);
    }

    public static ResponseEntity<GetRouletteListResponseDto> success(List<MyRandomEntity> myRandomEntities){
        GetRouletteListResponseDto responseBody = new GetRouletteListResponseDto(myRandomEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
