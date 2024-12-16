package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.review.PatchReviewCommentRequestDto;
import com.project.anywhere.dto.request.review.PostReviewCommentRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.review.GetReviewCommentListResponseDto;
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

    @Override
    public ResponseEntity<? super GetReviewCommentListResponseDto> getReviewCommentList(Integer reviewId) {

        List<ReviewCommentEntity> reviewCommentEntities = new ArrayList<>();

        try {

            boolean isExistedReviewPost = reviewPostRepository.existsByReviewId(reviewId);
            if(!isExistedReviewPost) return ResponseDto.noExistReviewPost();

            reviewCommentEntities = reviewCommentRepository.findByReviewIdOrderByReviewCommentIdAsc(reviewId);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReviewCommentListResponseDto.success(reviewCommentEntities);
        
    }

    @Override
    public ResponseEntity<ResponseDto> patchReviewComment(PatchReviewCommentRequestDto dto, Integer reviewId, Integer reviewCommentId, String userId) {

        try {

            ReviewCommentEntity reviewCommentEntity = reviewCommentRepository.findByReviewCommentId(reviewCommentId);
            if(reviewCommentEntity == null) return ResponseDto.noExistReviewComment();

            if(!reviewCommentEntity.getReviewId().equals(reviewId)) return ResponseDto.noExistReviewPost();
            if(!reviewCommentEntity.getReviewCommentWriter().equals(userId)) return ResponseDto.noPermission();

            reviewCommentEntity.patch(dto);
            reviewCommentEntity.setReviewCommentCreatedAt();

            reviewCommentRepository.save(reviewCommentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
        
    }

    @Override
    public ResponseEntity<ResponseDto> deleteReviewComment(Integer reviewId, Integer reviewCommentId, String userId) {

        try {

            ReviewCommentEntity reviewCommentEntity = reviewCommentRepository.findByReviewCommentId(reviewCommentId);
            if(reviewCommentEntity == null) return ResponseDto.noExistReviewComment();

            if(!reviewCommentEntity.getReviewId().equals(reviewId)) return ResponseDto.noExistReviewPost();
            if(!reviewCommentEntity.getReviewCommentWriter().equals(userId)) return ResponseDto.noPermission();

            reviewCommentEntity.delete();
            reviewCommentEntity.setReviewCommentCreatedAt();

            reviewCommentRepository.save(reviewCommentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
        
    }
    
}
