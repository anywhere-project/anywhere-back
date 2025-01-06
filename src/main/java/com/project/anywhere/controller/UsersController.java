package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.request.users.PatchUsersRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.users.GetUserInfoResponseDto;
import com.project.anywhere.dto.response.users.GetUsersResponseDto;
import com.project.anywhere.service.UsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/mypage")
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping(value = {"","/"})
    public ResponseEntity<? super GetUsersResponseDto> getUsers(
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetUsersResponseDto> response = usersService.getUser(userId);
        return response;

    }
    @GetMapping("/{userId}")
    public ResponseEntity<? super GetUsersResponseDto> getUser(
        @PathVariable("userId") String userId
    ){
        ResponseEntity<? super GetUsersResponseDto> response = usersService.getUser(userId);
        return response;

    }

    @PatchMapping(value = {"","/"})
    public ResponseEntity<ResponseDto> patchUser(
        @RequestBody @Valid PatchUsersRequestDto request,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = usersService.patchUser(request,userId);
        return response;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(@PathVariable("userId") String userId) {
        ResponseEntity<? super GetUserInfoResponseDto> response = usersService.getUserInfo(userId);
        return response;
    }
    
}
