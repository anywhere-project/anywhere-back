package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseDto;

public interface HashTagService {

    ResponseEntity<ResponseDto> postHashTag(String tagName, Integer reviewId, String userId);
    ResponseEntity<ResponseDto> deleteHashTag(Integer reviewId, Integer tagId, String userId);
    
}
