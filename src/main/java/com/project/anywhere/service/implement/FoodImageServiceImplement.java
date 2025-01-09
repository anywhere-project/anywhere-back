package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PostFoodImageRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.FoodImageEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.repository.FoodImageRepository;
import com.project.anywhere.repository.RecommendFoodRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.FoodImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodImageServiceImplement implements FoodImageService {

        private final RecommendFoodRepository foodRepository;
    private final UserRepository userRepository;
    private final FoodImageRepository imageRepository;
    
    @Override
    public ResponseEntity<ResponseDto> postFoodImage(PostFoodImageRequestDto dto, Integer foodId, String userId) {

            try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendFoodEntity foodEntity = foodRepository.findByFoodId(foodId);
            if (foodEntity == null) return ResponseDto.noExistRecommendFood();

            FoodImageEntity imageEntity = new FoodImageEntity(dto.getImageUrl(), foodId);
            imageRepository.save(imageEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteFoodImage(Integer imageId, Integer foodId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendFoodEntity foodEntity = foodRepository.findByFoodId(foodId);
            if (foodEntity == null) return ResponseDto.noExistRecommendFood();

            boolean isExistedImage = imageRepository.existsByImageId(imageId);
            if (!isExistedImage) return ResponseDto.noExistRecommendImage();

            imageRepository.deleteByImageId(imageId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
