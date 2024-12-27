package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PatchRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendFoodListResponseDto;
import com.project.anywhere.entity.FoodImageEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.FoodImageRepository;
import com.project.anywhere.repository.RecommendFoodRepository;
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
    private final FoodImageRepository imageRepository;

    @Override
    public ResponseEntity<ResponseDto> postRecommendFood(PostRecommendFoodRequestDto dto, Integer recommendId, String userId) {

        try {
            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendFoodEntity foodEntity = new RecommendFoodEntity(dto, recommendId);
            foodRepository.save(foodEntity);

            for (String image: dto.getImages()) {
                FoodImageEntity imageEntity = new FoodImageEntity(image, foodEntity.getFoodId());
                imageRepository.save(imageEntity);
            }

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
    public ResponseEntity<? super GetRecommendFoodListResponseDto> getRecommendFoodPosts(Integer recommendId) {
    
        List<RecommendFoodEntity> foodEntities = new ArrayList<>();
        
        try {

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost) return ResponseDto.noExistRecommendPost();
    
            foodEntities = foodRepository.findByRecommendId(recommendId);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return GetRecommendFoodListResponseDto.success(foodEntities);
    }
    
    
}
