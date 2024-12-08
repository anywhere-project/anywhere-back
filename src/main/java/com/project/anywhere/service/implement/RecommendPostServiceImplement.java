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
import com.project.anywhere.dto.response.recommend.GetRecommendPostResponseDto;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.RecommendAttractionRepository;
import com.project.anywhere.repository.RecommendFoodRepository;
import com.project.anywhere.repository.RecommendMissionRepository;
import com.project.anywhere.repository.RecommendPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendAttractionService;
import com.project.anywhere.service.RecommendFoodService;
import com.project.anywhere.service.RecommendMissionService;
import com.project.anywhere.service.RecommendPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendPostServiceImplement implements RecommendPostService {

    private final UserRepository userRepository;
    private final RecommendPostRepository postRepository;
    private final RecommendFoodService foodService;
    private final RecommendMissionService missionService;
    private final RecommendAttractionService attractionService;
    private final RecommendAttractionRepository attractionRepository;
    private final RecommendFoodRepository foodRepository;
    private final RecommendMissionRepository missionRepository;

    @Override
    public ResponseEntity<ResponseDto> postRecommendPost(PostRecommendPostRequestDto dto, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = new RecommendPostEntity(dto);
            postEntity.setRecommendWriter(userId);
            postRepository.save(postEntity);

            if (dto.getAttractions() != null) {
                for (PostRecommendAttractionRequestDto attractionDto: dto.getAttractions()) {
                    attractionService.postRecommendAttraction(attractionDto, postEntity.getRecommendId(), userId);
                }
            }

            if (dto.getFoods() != null) {
                for (PostRecommendFoodRequestDto foodDto: dto.getFoods()) {
                    foodService.postRecommendFood(foodDto, postEntity.getRecommendId(), userId);
                }
            }

            if (dto.getMissions() != null) {
                for (PostRecommendMissionRequestDto missionDto: dto.getMissions()) {
                    missionService.postRecommendMission(missionDto, postEntity.getRecommendId(), userId);
                }
            }

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchRecommendPost(PatchRecommendPostRequestDto dto, Integer recommendId, String userId) {

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

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
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
public ResponseEntity<? super GetRecommendPostListResponseDto> getRecommendPosts() {

    List<RecommendPostEntity> postEntities = postRepository.findAllByOrderByRecommendIdDesc();
    List<GetRecommendPostResponseDto> responseList = new ArrayList<>();

    try {

        for (RecommendPostEntity postEntity : postEntities) {
            RecommendAttractionEntity attractionEntity = attractionRepository.findByRecommendId(postEntity.getRecommendId());
            RecommendFoodEntity foodEntity = foodRepository.findByRecommendId(postEntity.getRecommendId());
            RecommendMissionEntity missionEntity = missionRepository.findByRecommendId(postEntity.getRecommendId());

            GetRecommendPostResponseDto responseDto = new GetRecommendPostResponseDto(postEntity, attractionEntity, foodEntity, missionEntity);
            responseList.add(responseDto);
        }

    } catch(Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }

    return GetRecommendPostListResponseDto.success(responseList);
}


}
