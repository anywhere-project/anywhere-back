package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.review.PostReviewCommentRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.ReviewCommentEntity;
import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.ReviewCommentRepository;
import com.project.anywhere.repository.ReviewPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.ReviewCommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewCommentServiceImplement implements ReviewCommentService{

    private final UserRepository userRepository;
    private final ReviewPostRepository reviewPostRepository;
    private final ReviewCommentRepository reviewCommentRepository;

    @Override
    public ResponseEntity<ResponseDto> postReviewComment(PostReviewCommentRequestDto dto, Integer reviewId, String userId) {

        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if(usersEntity == null) return ResponseDto.noExistUserId();

            ReviewPostEntity reviewPostEntity = reviewPostRepository.findByReviewId(reviewId);
            if(reviewPostEntity == null) return ResponseDto.noExistReview();

            if(dto.getParentCommentId() != null) {
                ReviewCommentEntity parentCommentEntity = reviewCommentRepository.findByReviewCommentId(dto.getParentCommentId());
                if(parentCommentEntity == null) return ResponseDto.noExistParentComment();
                ReviewCommentEntity reviewCommentEntity = new ReviewCommentEntity(dto, parentCommentEntity, userId, reviewId);
                reviewCommentRepository.save(reviewCommentEntity);
            } else {
                Integer orderNumber = reviewCommentRepository.findTopByOrderByOrderNumberDesc().getOrderNumber();
                if(orderNumber == null) orderNumber = 0;
                ReviewCommentEntity reviewCommentEntity = new ReviewCommentEntity(dto, userId, reviewId, orderNumber);
                reviewCommentRepository.save(reviewCommentEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
        
    }
    
}
