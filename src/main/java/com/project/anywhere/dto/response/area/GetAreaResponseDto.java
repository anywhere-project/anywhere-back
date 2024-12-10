package com.project.anywhere.dto.response.area;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.Areas;
import com.project.anywhere.common.object.RecommendPost;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.AreasEntity;

import lombok.Getter;

@Getter
public class GetAreaResponseDto extends ResponseDto {
    
    private List<Areas> areas;

    private GetAreaResponseDto(List<Areas> areas) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.areas = areas;

    }

    public static ResponseEntity<GetAreaResponseDto> success(List<Areas> areas) {
        GetAreaResponseDto responseBody = new GetAreaResponseDto(areas);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
