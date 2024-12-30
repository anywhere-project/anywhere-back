package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.mission.GetMissionResponseDto;

public interface MissionService {
    ResponseEntity<? super GetMissionResponseDto> getMission();
}
