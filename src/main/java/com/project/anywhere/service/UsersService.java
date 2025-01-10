package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.users.PatchUsersRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.users.GetUserInfoResponseDto;
import com.project.anywhere.dto.response.users.GetUsersResponseDto;

public interface UsersService {

    ResponseEntity<? super GetUsersResponseDto> getUser(String userId);
    ResponseEntity<ResponseDto> patchUser(PatchUsersRequestDto dto, String userId);
    ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId);
    
}
