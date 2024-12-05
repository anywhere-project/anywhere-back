package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.request.reviewImages.PostReviewImagesRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.ReviewEntity;
import com.project.anywhere.entity.ReviewImagesEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.ReviewImagesRepository;
import com.project.anywhere.repository.ReviewRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService{
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImagesRepository reviewImagesRepository;

    @Override
    public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto,
            String userId) {
                try {

                    ReviewEntity reviewEntity = new ReviewEntity(dto);
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
