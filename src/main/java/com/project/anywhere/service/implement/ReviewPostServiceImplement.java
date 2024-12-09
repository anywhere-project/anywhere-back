package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.review.PatchReviewRequestDto;
import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.review.GetReviewListResponseDto;
import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.entity.HashTagEntity;
import com.project.anywhere.entity.ReviewImagesEntity;
import com.project.anywhere.repository.HashTagRepository;
import com.project.anywhere.repository.ReviewImagesRepository;
import com.project.anywhere.repository.ReviewPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.ReviewPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewPostServiceImplement implements ReviewPostService{

    private final UserRepository userRepository;
    private final ReviewPostRepository reviewRepository;
    private final ReviewImagesRepository reviewImagesRepository;
    private final HashTagRepository hashTagRepository;

    @Override
    public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, String userId) {
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

        for (String tagName: dto.getHashtags()) {
            HashTagEntity hashTagEntity = new HashTagEntity(tagName, reviewEntity.getReviewId());
            hashTagRepository.save(hashTagEntity);
        }

    } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }
    return ResponseDto.success();

    }
    
    @Override
    public ResponseEntity<? super GetReviewListResponseDto> getReviewList() {
        List<ReviewPostEntity> reviewPostEntities = new ArrayList<>();
        List<ReviewImagesEntity> reviewImagesEntities = new ArrayList<>();
        List<HashTagEntity> hashTagEntities = new ArrayList<>();

        try {

            reviewPostEntities = reviewRepository.findByOrderByReviewIdDesc();
            reviewImagesEntities = reviewImagesRepository.findAll();
            hashTagEntities = hashTagRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReviewListResponseDto.success(reviewPostEntities, reviewImagesEntities, hashTagEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> patchReview(Integer reviewId, String userId, PatchReviewRequestDto dto) {
        try {
            ReviewPostEntity reviewPostEntity = reviewRepository.findByReviewId(reviewId);
            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if(reviewPostEntity == null) return ResponseDto.noExistReview();
            if(usersEntity == null) return ResponseDto.noExistUserId();

            if (!reviewPostEntity.getReviewWriter().equals(userId)) {
                if (usersEntity.getIsAdmin() != true) {
                    return ResponseDto.noPermission();
                } 
            }

            reviewPostEntity.ReviewPatchEntity(dto);
            reviewRepository.save(reviewPostEntity);

            ReviewImagesEntity reviewImagesEntity = reviewImagesRepository.findByReviewId(reviewId);
            if (reviewImagesEntity != null) {
                reviewImagesEntity.setImageUrl(dto.getImageUrl());
                reviewImagesRepository.save(reviewImagesEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    
}
