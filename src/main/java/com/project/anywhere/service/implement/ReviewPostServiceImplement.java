package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.review.PatchReviewRequestDto;
import com.project.anywhere.dto.request.review.PostReviewRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.review.GetReviewListResponseDto;
import com.project.anywhere.dto.response.review.GetReviewResponseDto;
import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.entity.HashTagEntity;
import com.project.anywhere.entity.ReviewImagesEntity;
import com.project.anywhere.entity.ReviewLikeEntity;
import com.project.anywhere.repository.HashTagRepository;
import com.project.anywhere.repository.ReviewImagesRepository;
import com.project.anywhere.repository.ReviewLikeRepository;
import com.project.anywhere.repository.ReviewPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.repository.resultset.GetImageResultSet;
import com.project.anywhere.repository.resultset.GetReviewResultSet;
import com.project.anywhere.service.ReviewPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewPostServiceImplement implements ReviewPostService{

    private final UserRepository userRepository;
    private final ReviewPostRepository reviewRepository;
    private final ReviewImagesRepository reviewImagesRepository;
    private final HashTagRepository hashTagRepository;
    private final ReviewLikeRepository likeRepository;

    @Override
    public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, String userId) {

        try {

            ReviewPostEntity reviewEntity = new ReviewPostEntity(dto);
            UsersEntity userEntity = userRepository.findByUserId(userId);

            if(userEntity == null) return ResponseDto.noExistUserId();
            
            reviewEntity.setReviewWriter(userId);
            reviewRepository.save(reviewEntity);

            for (String image: dto.getImages()) {
                Integer maxImageOrder = reviewImagesRepository.findMaxImageOrderByReviewId(reviewEntity.getReviewId());
                ReviewImagesEntity reviewImagesEntity = new ReviewImagesEntity(image, reviewEntity.getReviewId(), maxImageOrder + 1);
                reviewImagesRepository.save(reviewImagesEntity);
            }

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
        List<ReviewLikeEntity> likeEntities = new ArrayList<>();

        try {

            reviewPostEntities = reviewRepository.findByOrderByReviewIdDesc();
            reviewImagesEntities = reviewImagesRepository.findAll();
            hashTagEntities = hashTagRepository.findAll();
            likeEntities = likeRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReviewListResponseDto.success(reviewPostEntities, reviewImagesEntities, hashTagEntities, likeEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> patchReview(Integer reviewId, String userId, PatchReviewRequestDto dto) {
        try {
            ReviewPostEntity reviewPostEntity = reviewRepository.findByReviewId(reviewId);
            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if(reviewPostEntity == null) return ResponseDto.noExistReviewPost();
            if(usersEntity == null) return ResponseDto.noExistUserId();

            if (!reviewPostEntity.getReviewWriter().equals(userId)) {
                if (usersEntity.getIsAdmin() != true) {
                    return ResponseDto.noPermission();
                } 
            }

            reviewPostEntity.ReviewPatchEntity(dto);
            reviewRepository.save(reviewPostEntity);

            reviewImagesRepository.deleteByReviewId(reviewId);
            hashTagRepository.deleteByReviewId(reviewId);

            for (String image: dto.getImages()) {
                Integer maxImageOrder = reviewImagesRepository.findMaxImageOrderByReviewId(reviewPostEntity.getReviewId());
                ReviewImagesEntity reviewImagesEntity = new ReviewImagesEntity(image, reviewPostEntity.getReviewId(), maxImageOrder + 1);
                reviewImagesRepository.save(reviewImagesEntity);
            }

            for (String tagName: dto.getHashtags()) {
                HashTagEntity hashTagEntity = new HashTagEntity(tagName, reviewPostEntity.getReviewId());
                hashTagRepository.save(hashTagEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteReview(Integer reviewId, String userId) {
        try {
            ReviewPostEntity reviewPostEntity = reviewRepository.findByReviewId(reviewId);
            UsersEntity usersEntity = userRepository.findByUserId(userId);

            if(usersEntity == null) return ResponseDto.noExistUserId();
            if(reviewPostEntity == null) return ResponseDto.noExistReviewPost();
            
            reviewRepository.delete(reviewPostEntity);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetReviewResponseDto> getReview(Integer reviewId) {
        GetReviewResultSet resultSet = null;
        List<GetImageResultSet> imageResultSet = new ArrayList<>();
        List<String> hashTagResultSet = new ArrayList<>();
        List<String> likeEntities = new ArrayList<>();

        try {
            resultSet = reviewRepository.getReview(reviewId);
            if(resultSet == null) return ResponseDto.noExistReviewPost();

            imageResultSet = reviewImagesRepository.getImages(reviewId);
            if(imageResultSet == null) return ResponseDto.noExistReviewImage();

            hashTagResultSet = hashTagRepository.getHashTags(reviewId);
            if(hashTagResultSet == null) return ResponseDto.noExistReviewHashTag();

            likeEntities = likeRepository.getReviewLikes(reviewId);

            ReviewPostEntity reviewPostEntity = reviewRepository.findByReviewId(reviewId);
            if(reviewPostEntity==null) return ResponseDto.noExistReviewPost();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReviewResponseDto.success(resultSet, imageResultSet, hashTagResultSet, likeEntities);
    }

    
}
