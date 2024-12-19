package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PatchRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendFoodListResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendFoodPostResponseDto;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.RecommendFoodRepository;
import com.project.anywhere.repository.RecommendImageRepository;
import com.project.anywhere.repository.RecommendPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendFoodService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendFoodServiceImplement implements RecommendFoodService {

    private final UserRepository userRepository;
    private final RecommendPostRepository postRepository;
    private final RecommendFoodRepository foodRepository;
    private final RecommendImageRepository imageRepository;

    @Override
    public ResponseEntity<ResponseDto> postRecommendFood(PostRecommendFoodRequestDto dto, Integer recommendId, String userId) {

        try {
            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendFoodEntity foodEntity = new RecommendFoodEntity(dto, recommendId);
            foodRepository.save(foodEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchRecommendFood(PatchRecommendFoodRequestDto dto, Integer recommendId, Integer foodId, String userId) {

        try {
            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();
            if (!postEntity.getRecommendWriter().equals(userId)) return ResponseDto.noPermission();

            RecommendFoodEntity foodEntity = foodRepository.findByFoodId(foodId);
            if (foodEntity == null) return ResponseDto.noExistRecommendFood();

            foodEntity.patch(dto);
            foodRepository.save(foodEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteRecommendFood(Integer recommendId, Integer foodId, String userId) {

        try {
            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();
            if (!postEntity.getRecommendWriter().equals(userId)) return ResponseDto.noPermission();

            RecommendFoodEntity foodEntity = foodRepository.findByFoodId(foodId);
            if (foodEntity == null) return ResponseDto.noExistRecommendFood();

            foodRepository.deleteByFoodId(foodId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetRecommendFoodPostResponseDto> getRecommendFoodPost(Integer recommendId) {
        RecommendPostEntity postEntity = null;
        List<RecommendFoodEntity> foodEntities = new ArrayList<>();
        List<RecommendImageEntity> imageEntities = new ArrayList<>();

        try {

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost) return ResponseDto.noExistRecommendPost();

            postEntity = postRepository.findByRecommendId(recommendId);
            foodEntities = foodRepository.findByRecommendId(recommendId);
            imageEntities = imageRepository.findByRecommendIdOrderByImageOrderAsc(recommendId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRecommendFoodPostResponseDto.success(postEntity, foodEntities, imageEntities);
    }

    @Override
    public ResponseEntity<? super GetRecommendFoodListResponseDto> getRecommendFoodPosts() {
        List<RecommendPostEntity> postEntities = new ArrayList<>();
        List<RecommendFoodEntity> foodEntities = new ArrayList<>();
        List<RecommendImageEntity> imageEntities = new ArrayList<>();
    
        try {

            postEntities = postRepository.findAll();
            
            for (RecommendPostEntity postEntity : postEntities) {
                List<RecommendFoodEntity> foods = foodRepository.findByRecommendId(postEntity.getRecommendId());
                foodEntities.addAll(foods);
                
                List<RecommendImageEntity> images = imageRepository.findByRecommendIdOrderByImageOrderAsc(postEntity.getRecommendId());
                imageEntities.addAll(images);
            }
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return GetRecommendFoodListResponseDto.success(postEntities, foodEntities, imageEntities);
    }
    
}
