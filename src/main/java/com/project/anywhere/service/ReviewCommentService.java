package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.review.PatchReviewCommentRequestDto;
import com.project.anywhere.dto.request.review.PostReviewCommentRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.review.GetReviewCommentListResponseDto;

public interface ReviewCommentService {

    ResponseEntity<ResponseDto> postReviewComment(PostReviewCommentRequestDto dto, Integer reviewId, String userId);
    ResponseEntity<? super GetReviewCommentListResponseDto> getReviewCommentList(Integer reviewId);
    ResponseEntity<ResponseDto> patchReviewComment(PatchReviewCommentRequestDto dto, Integer reviewId, Integer reviewCommentId, String userId);

}
