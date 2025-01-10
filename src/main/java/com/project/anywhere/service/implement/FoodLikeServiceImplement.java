package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.FoodLikeEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.FoodLikeRepository;
import com.project.anywhere.repository.RecommendFoodRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.FoodLikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodLikeServiceImplement implements FoodLikeService {

    private final UserRepository userRepository;
    private final FoodLikeRepository likeRepository;
    private final RecommendFoodRepository foodRepository;

    @Override
    public ResponseEntity<ResponseDto> foodLike(Integer foodId, String userId) {
        
        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            RecommendFoodEntity foodEntity = foodRepository.findByFoodId(foodId);
            if (foodEntity == null) return ResponseDto.noExistFoodId();

            boolean isLiked = likeRepository.existsByUserIdAndFoodId(userId, foodId);
            if (isLiked) {
                likeRepository.deleteByUserIdAndFoodId(userId, foodId);
                foodEntity.setFoodLike(foodEntity.getFoodLike() - 1);
                foodRepository.save(foodEntity);
                return ResponseDto.likeUnclick();
            } else {
                FoodLikeEntity likeEntity = new FoodLikeEntity(userId, foodId);
                likeRepository.save(likeEntity);
                foodEntity.setFoodLike(foodEntity.getFoodLike() + 1);
                foodRepository.save(foodEntity);
                return ResponseDto.likeClick();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
