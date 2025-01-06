package com.project.anywhere.dto.response.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.UsersEntity;

import lombok.Getter;

@Getter
public class GetUserInfoResponseDto extends ResponseDto {
    private String userId;
    private String nickname;

    public GetUserInfoResponseDto(UsersEntity usersEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = usersEntity.getUserId();
        this.nickname = usersEntity.getNickname();
    }

    public static ResponseEntity<GetUserInfoResponseDto> success(UsersEntity usersEntity) {
        GetUserInfoResponseDto responseBody = new GetUserInfoResponseDto(usersEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
