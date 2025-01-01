package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PatchRecommendPostRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendPostListResponseDto;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.RecommendPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendAttractionService;
import com.project.anywhere.service.RecommendFoodService;
import com.project.anywhere.service.RecommendMissionService;
import com.project.anywhere.service.RecommendPostService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendPostServiceImplement implements RecommendPostService {

    private final UserRepository userRepository;
    private final RecommendPostRepository postRepository;
    private final RecommendAttractionService attractionService;
    private final RecommendMissionService missionService;
    private final RecommendFoodService foodService;

    @Override
    public ResponseEntity<ResponseDto> postRecommendPost(PostRecommendPostRequestDto dto, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = new RecommendPostEntity(dto);
            postEntity.setRecommendWriter(userId);
            postRepository.save(postEntity);

            String category = dto.getRecommendCategory();

            if ("attraction".equals(category)) {
                for (PostRecommendAttractionRequestDto postDto: dto.getAttractions()) {
                    attractionService.postRecommendAttraction(postDto, postEntity.getRecommendId(), userId);
                }
            } else if ("food".equals(category)) {
                for (PostRecommendFoodRequestDto postDto: dto.getFoods()) {
                    foodService.postRecommendFood(postDto, postEntity.getRecommendId(), userId);
                }
            } else if ("mission".equals(category)) {
                for (PostRecommendMissionRequestDto postDto: dto.getMissions()) {
                    missionService.postRecommendMission(postDto, postEntity.getRecommendId(), userId);
                }
            }

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchRecommendPost(PatchRecommendPostRequestDto dto, String category, Integer recommendId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost) return ResponseDto.noExistRecommendPost();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();
            if (!postEntity.getRecommendWriter().equals(userId)) return ResponseDto.noPermission();

            postEntity.patch(dto);
            postRepository.save(postEntity);

            if ("attraction".equals(category)) {
                for (PostRecommendAttractionRequestDto postDto: dto.getAttractions()) {
                    attractionService.postRecommendAttraction(postDto, postEntity.getRecommendId(), userId);
                }
            } else if ("food".equals(category)) {
                for (PostRecommendFoodRequestDto postDto: dto.getFoods()) {
                    foodService.postRecommendFood(postDto, postEntity.getRecommendId(), userId);
                }
            } else if ("mission".equals(category)) {
                for (PostRecommendMissionRequestDto postDto: dto.getMissions()) {
                    missionService.postRecommendMission(postDto, postEntity.getRecommendId(), userId);
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteRecommendPost(Integer recommendId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost) return ResponseDto.noExistRecommendPost();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();
            if (!postEntity.getRecommendWriter().equals(userId)) return ResponseDto.noPermission();

            postRepository.deleteByRecommendId(recommendId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetRecommendPostListResponseDto> getRecommendPosts(String category) {
        List<RecommendPostEntity> postEntities = new ArrayList<>();

        try {

            postEntities = postRepository.findByRecommendCategory(category);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRecommendPostListResponseDto.success(postEntities);
    }

}