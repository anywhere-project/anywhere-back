package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.area.GetAreaResponseDto;


public interface AreaService {
    ResponseEntity<? super GetAreaResponseDto> getArea();
}