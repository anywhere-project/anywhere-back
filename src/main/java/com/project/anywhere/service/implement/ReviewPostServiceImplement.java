package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;

import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.ReviewPostRespsitory;
import com.project.anywhere.entity.ReviewImagesEntity;
import com.project.anywhere.repository.ReviewImagesRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.ReviewPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewPostServiceImplement implements ReviewPostService{

    private final UserRepository userRepository;
    private final ReviewPostRespsitory reviewRepository;
    private final ReviewImagesRepository reviewImagesRepository;

    @Override
    public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto,
            String userId) {
                try {

                    ReviewPostEntity reviewEntity = new ReviewPostEntity(dto);
                    ReviewImagesEntity reviewImagesEntity = new ReviewImagesEntity();
                    UsersEntity userEntity = userRepository.findByUserId(userId);

                    if(userEntity == null) return ResponseDto.noExistUserId();
                    
                    reviewEntity.setReviewWriter(userId);
                    reviewRepository.save(reviewEntity);
                    reviewImagesEntity.setImageUrl(dto.getImageUrl());
                    reviewImagesEntity.setReviewId(reviewEntity.getReviewId());
                    reviewImagesRepository.save(reviewImagesEntity);

                } catch (Exception exception) {
                    exception.printStackTrace();
                    return ResponseDto.databaseError();
                }
                return ResponseDto.success();

    }
    
}
