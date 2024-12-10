package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.hashtag.PostHashTagRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.hashtag.GetHashTagListResponseDto;

public interface HashTagService {

    ResponseEntity<ResponseDto> postHashTag(PostHashTagRequestDto dto, Integer reviewId, String userId);
    ResponseEntity<ResponseDto> deleteHashTag(Integer reviewId, Integer tagId, String userId);
    ResponseEntity<? super GetHashTagListResponseDto> getHashTags();
    
}
