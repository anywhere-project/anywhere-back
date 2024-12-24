package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.recommend.PatchRecommendImageRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendImageRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendImageListResponseDto;

public interface RecommendImageService {
    
    ResponseEntity<ResponseDto> postRecommendImage(PostRecommendImageRequestDto dto, Integer recommendId, String userId);
    ResponseEntity<ResponseDto> patchRecommendImage(PatchRecommendImageRequestDto dto, Integer recommendId, Integer imageId, String userId);
    ResponseEntity<ResponseDto> deleteRecommendImage(Integer recommendId, Integer imageId, String userId);
    ResponseEntity<? super GetRecommendImageListResponseDto> getRecommendImages(Integer recommendId);

}
