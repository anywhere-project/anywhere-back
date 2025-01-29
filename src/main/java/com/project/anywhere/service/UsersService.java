package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.auth.TelAuthCheckRequestDto;
import com.project.anywhere.dto.request.users.PatchPasswordRequestDto;
import com.project.anywhere.dto.request.users.PatchTelAuthRequestDto;
import com.project.anywhere.dto.request.users.PatchUsersRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.users.GetUserInfoResponseDto;
import com.project.anywhere.dto.response.users.GetUsersResponseDto;

public interface UsersService {

    ResponseEntity<ResponseDto> patchTelAuth(PatchTelAuthRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchTelAuthCheck(TelAuthCheckRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchPassword(PatchPasswordRequestDto dto, String userId);
    ResponseEntity<? super GetUsersResponseDto> getUser(String userId);
    ResponseEntity<ResponseDto> patchUser(PatchUsersRequestDto dto, String userId);
    ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId);
    
}
