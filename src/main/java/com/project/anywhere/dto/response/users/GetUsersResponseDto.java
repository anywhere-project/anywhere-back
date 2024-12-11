package com.project.anywhere.dto.response.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.UsersEntity;

import lombok.Getter;

@Getter
public class GetUsersResponseDto extends ResponseDto{
    private String userId;
    private String telNumber;
    private String password;
    private String name;
    private String nickname;
    private String profileImage;
    private Boolean isAdmin;
    private String userStatus;
    
    public GetUsersResponseDto(UsersEntity usersEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = usersEntity.getUserId();
        this.telNumber = usersEntity.getTelNumber();
        this.password = usersEntity.getPassword();
        this.name = usersEntity.getName();
        this.nickname = usersEntity.getNickname();
        this.profileImage = usersEntity.getProfileImage();
        this.isAdmin = usersEntity.getIsAdmin();
        this.userStatus = usersEntity.getUserStatus();
        
    }

    public static ResponseEntity<GetUsersResponseDto> sucess(UsersEntity usersEntity){
        GetUsersResponseDto responseBody = new GetUsersResponseDto(usersEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
