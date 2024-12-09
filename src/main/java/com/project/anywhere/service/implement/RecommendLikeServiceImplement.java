package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.RecommendLikeEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.RecommendLikeRepository;
import com.project.anywhere.repository.RecommendPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendLikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendLikeServiceImplement implements RecommendLikeService {

    private final UserRepository userRepository;
    private final RecommendLikeRepository likeRepository;
    private final RecommendPostRepository postRepository;

    @Override
    public ResponseEntity<ResponseDto> recommendLike(String userId, Integer recommendId) {
        
        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();

            boolean isLiked = likeRepository.existsByUserIdAndRecommendId(userId, recommendId);
            if (isLiked) {
                likeRepository.deleteByUserIdAndRecommendId(userId, recommendId);
                postEntity.setRecommendLikeCount(postEntity.getRecommendLikeCount() - 1);
                postRepository.save(postEntity);
                return ResponseDto.likeUnclick();
            } else {
                RecommendLikeEntity likeEntity = new RecommendLikeEntity(userId, recommendId);
                likeRepository.save(likeEntity);
                postEntity.setRecommendLikeCount(postEntity.getRecommendLikeCount() + 1);
                postRepository.save(postEntity);
                return ResponseDto.likeClick();
            }

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }
    
}
