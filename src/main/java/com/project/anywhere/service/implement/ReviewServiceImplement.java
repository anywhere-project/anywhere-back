package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.request.reviewImages.PostReviewImagesRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.ReviewEntity;
import com.project.anywhere.entity.UserEntity;
import com.project.anywhere.repository.ReviewRespsitory;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService{
    private final UserRepository userRepository;
    private final ReviewRespsitory reviewRepository;

    @Override
    public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, PostReviewImagesRequestDto dto2,
            String userId) {
                try {

                    ReviewEntity reviewEntity = new ReviewEntity(dto);
                    UserEntity userEntity = userRepository.findByUserId(userId);

                    if(userEntity == null) return ResponseDto.noExistUserId();
                    
                    reviewEntity.setReviewPostCreatedAt();
                    reviewEntity.setReviewWriter(userId);
                    reviewRepository.save(reviewEntity);

                } catch (Exception exception) {
                    exception.printStackTrace();
                    return ResponseDto.databaseError();
                }
                return ResponseDto.success();

    }
    
}
