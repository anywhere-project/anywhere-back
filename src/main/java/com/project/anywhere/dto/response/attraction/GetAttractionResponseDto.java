package com.project.anywhere.dto.response.attraction;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.Attractions;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetAttractionResponseDto extends ResponseDto {

    private List<Attractions> attractions;

    private GetAttractionResponseDto(List<Attractions> attractions) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.attractions = attractions;
    }

    public static ResponseEntity<GetAttractionResponseDto> success(List<Attractions> attractions) {
        GetAttractionResponseDto responseBody = new GetAttractionResponseDto(attractions);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
