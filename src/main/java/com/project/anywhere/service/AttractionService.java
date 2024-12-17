package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.attraction.GetAttractionResponseDto;

public interface AttractionService {
    ResponseEntity<? super GetAttractionResponseDto> getAttraction();
}
