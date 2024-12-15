package com.project.anywhere.service;

import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.request.review.PostReviewCommentRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

public interface ReviewCommentService {

    ResponseEntity<ResponseDto> postReviewComment(PostReviewCommentRequestDto dto, Integer reviewId, String userId);

}
