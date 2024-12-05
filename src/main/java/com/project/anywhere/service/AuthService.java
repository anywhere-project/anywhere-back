package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.auth.IdCheckRequestDto;
import com.project.anywhere.dto.request.auth.SignInRequestDto;
import com.project.anywhere.dto.request.auth.SignUpRequestDto;
import com.project.anywhere.dto.request.auth.TelAuthCheckRequestDto;
import com.project.anywhere.dto.request.auth.TelAuthRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.auth.SignInResponseDto;

public interface AuthService {
    
    ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto);
    ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
