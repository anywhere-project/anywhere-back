package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.response.area.GetAreaResponseDto;
import com.project.anywhere.dto.response.mission.GetMissionResponseDto;
import com.project.anywhere.service.MissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mission")
public class MissionController {

    private final MissionService missionService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<? super GetMissionResponseDto> getMission() {
        ResponseEntity<? super GetMissionResponseDto> response = missionService.getMission();
        return response;
    }
}