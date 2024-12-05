package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.request.reviewImages.PostReviewImagesRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.ReviewPostRespsitory;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.ReviewPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewPostServiceImplement implements ReviewPostService{
    private final UserRepository userRepository;
    private final ReviewPostRespsitory reviewRepository;

    @Override
    public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, PostReviewImagesRequestDto dto2,
            String userId) {
                try {

                    ReviewPostEntity reviewEntity = new ReviewPostEntity(dto);
                    UsersEntity userEntity = userRepository.findByUserId(userId);

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
