package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.ReviewLikeEntity;
import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.ReviewLikeRepository;
import com.project.anywhere.repository.ReviewPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.ReviewLikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewLikeServiceImplement implements ReviewLikeService {

    private final UserRepository userRepository;
    private final ReviewPostRepository postRepository;
    private final ReviewLikeRepository likeRepository;
    
    @Override
    public ResponseEntity<ResponseDto> reviewLike(String userId, Integer reviewId) {

        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            ReviewPostEntity postEntity = postRepository.findByReviewId(reviewId);
            if (postEntity == null) return ResponseDto.noExistReviewPost();

            boolean isLiked = likeRepository.existsByUserIdAndReviewId(userId, reviewId);
            if (isLiked) {
                likeRepository.deleteByUserIdAndReviewId(userId, reviewId);
                postEntity.setReviewLikeCount(postEntity.getReviewLikeCount() - 1);
                postRepository.save(postEntity);
                return ResponseDto.likeUnclick();
            } else {
                ReviewLikeEntity likeEntity = new ReviewLikeEntity(userId, reviewId);
                likeRepository.save(likeEntity);
                postEntity.setReviewLikeCount(postEntity.getReviewLikeCount() + 1);
                postRepository.save(postEntity);
                return ResponseDto.likeClick();
            }

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }
    
}
