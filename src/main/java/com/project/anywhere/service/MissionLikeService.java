package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseDto;

public interface MissionLikeService {

    ResponseEntity<ResponseDto> missionLike(Integer missionId, String userId);
}
