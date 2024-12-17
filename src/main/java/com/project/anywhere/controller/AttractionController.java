package com.project.anywhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.anywhere.dto.response.area.GetAreaResponseDto;
import com.project.anywhere.dto.response.attraction.GetAttractionResponseDto;
import com.project.anywhere.service.AttractionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attraction")
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<? super GetAttractionResponseDto> getAttraction() {
        ResponseEntity<? super GetAttractionResponseDto> response = attractionService.getAttraction();
        return response;
    }
}
