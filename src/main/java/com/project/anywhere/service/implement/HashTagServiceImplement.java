package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.hashtag.PostHashTagRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.HashTagEntity;
import com.project.anywhere.repository.HashTagRepository;
import com.project.anywhere.repository.ReviewPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.HashTagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashTagServiceImplement implements HashTagService {

    private final UserRepository userRepository;
    private final ReviewPostRepository postRespsitory;
    private final HashTagRepository hashTagRepository;
    
    @Override
    public ResponseEntity<ResponseDto> postHashTag(PostHashTagRequestDto dto, Integer reviewId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            boolean isExistedReviewPost = postRespsitory.existsByReviewId(reviewId);
            if (!isExistedReviewPost) return ResponseDto.noExistReviewPost();

            HashTagEntity hashTagEntity = new HashTagEntity(dto);
            hashTagRepository.save(hashTagEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteHashTag(Integer reviewId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            boolean isExistedReviewPost = postRespsitory.existsByReviewId(reviewId);
            if (!isExistedReviewPost) return ResponseDto.noExistReviewPost();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
