package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.response.area.GetAreaResponseDto;
import com.project.anywhere.service.AreaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/area")
public class AreaController {

    private final AreaService areaService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<? super GetAreaResponseDto> getArea() {
        ResponseEntity<? super GetAreaResponseDto> response = areaService.getArea();
        return response;
    }
}