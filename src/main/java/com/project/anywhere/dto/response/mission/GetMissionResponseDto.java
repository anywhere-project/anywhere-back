package com.project.anywhere.dto.response.mission;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.Missions;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetMissionResponseDto extends ResponseDto {

    private List<Missions> missions;

    private GetMissionResponseDto(List<Missions> missions) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.missions = missions;
    }

    public static ResponseEntity<GetMissionResponseDto> success(List<Missions> missions) {
        GetMissionResponseDto responseBody = new GetMissionResponseDto(missions);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
