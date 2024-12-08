package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseDto;

public interface ReviewScrapService {

    ResponseEntity<ResponseDto> reviewScrap(String userId, Integer reviewId);
    
}
