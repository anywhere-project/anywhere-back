package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PostAttractionImageRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

public interface AttractionImageService {
    
    ResponseEntity<ResponseDto> postAttractionImage(PostAttractionImageRequestDto dto, Integer attractionId, String userId);
    ResponseEntity<ResponseDto> deleteAttractionImage(Integer imageId, Integer attractionId, String userId);

}
