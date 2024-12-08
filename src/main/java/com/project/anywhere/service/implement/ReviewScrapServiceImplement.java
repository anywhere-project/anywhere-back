package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.entity.ReviewScrapEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.ReviewPostRespsitory;
import com.project.anywhere.repository.ReviewScrapRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.ReviewScrapService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewScrapServiceImplement implements ReviewScrapService {
    
    private final UserRepository userRepository;
    private final ReviewPostRespsitory postRepository;
    private final ReviewScrapRepository scrapRepository;

    @Override
    public ResponseEntity<ResponseDto> reviewScrap(String userId, Integer reviewId) {
        
        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            ReviewPostEntity postEntity = postRepository.findByReviewId(reviewId);
            if (postEntity == null) return ResponseDto.noExistReviewPost();

            boolean isScraped = scrapRepository.existsByUserIdAndReviewId(userId, reviewId);
            if (isScraped) {
                scrapRepository.deleteByUserIdAndReviewId(userId, reviewId);
                return ResponseDto.scrapUnclick();
            } else {
                ReviewScrapEntity scrapEntity = new ReviewScrapEntity(userId, reviewId);
                scrapRepository.save(scrapEntity);
                return ResponseDto.scrapClick();
            }

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }
    
}
