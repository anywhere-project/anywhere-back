package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseDto;

public interface AttractionLikeService {
    
    ResponseEntity<ResponseDto> attractionLike(Integer attractionId, String userId);

}
