package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PatchRecommendImageRequestDto;
import com.project.anywhere.dto.request.recommend.PatchRecommendPostRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendFoodRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendImageRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendPostListResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendPostResponseDto;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.RecommendAttractionRepository;
import com.project.anywhere.repository.RecommendFoodRepository;
import com.project.anywhere.repository.RecommendImageRepository;
import com.project.anywhere.repository.RecommendMissionRepository;
import com.project.anywhere.repository.RecommendPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendAttractionService;
import com.project.anywhere.service.RecommendFoodService;
import com.project.anywhere.service.RecommendImageService;
import com.project.anywhere.service.RecommendMissionService;
import com.project.anywhere.service.RecommendPostService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendPostServiceImplement implements RecommendPostService {

    private final UserRepository userRepository;
    private final RecommendPostRepository postRepository;
    private final RecommendFoodService foodService;
    private final RecommendMissionService missionService;
    private final RecommendAttractionService attractionService;
    private final RecommendImageService imageService;
    private final RecommendAttractionRepository attractionRepository;
    private final RecommendFoodRepository foodRepository;
    private final RecommendMissionRepository missionRepository;
    private final RecommendImageRepository imageRepository;

    @Override
    public ResponseEntity<ResponseDto> postRecommendPost(PostRecommendPostRequestDto dto, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = new RecommendPostEntity(dto);
            postEntity.setRecommendWriter(userId);
            postRepository.save(postEntity);

            if (dto.getAttraction() != null) {
                attractionService.postRecommendAttraction(dto.getAttraction(), postEntity.getRecommendId(), userId);
            }

            if (dto.getFood() != null) {
                foodService.postRecommendFood(dto.getFood(), postEntity.getRecommendId(), userId);
            }

            if (dto.getMission() != null) {
                missionService.postRecommendMission(dto.getMission(), postEntity.getRecommendId(), userId);
            }

            if (dto.getImages() != null) {
                for (PostRecommendImageRequestDto imageDto: dto.getImages()) {
                    imageService.postRecommendImage(imageDto, postEntity.getRecommendId(), userId);
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

            RecommendAttractionEntity attractionEntity = attractionRepository.findByRecommendId(recommendId);
            RecommendFoodEntity foodEntity = foodRepository.findByRecommendId(recommendId);
            RecommendMissionEntity missionEntity = missionRepository.findByRecommendId(recommendId);
            List<RecommendImageEntity> imageEntities = imageRepository.findByRecommendIdOrderByImageOrderAsc(recommendId);

            if (dto.getAttraction() != null) {
                if (attractionEntity == null) {
                    PostRecommendAttractionRequestDto postDto = new PostRecommendAttractionRequestDto();
                    postDto.setAttractionName(dto.getAttraction().getAttractionName());
                    postDto.setAttractionAddress(dto.getAttraction().getAttractionAddress());
                    postDto.setAttractionContent(dto.getAttraction().getAttractionContent());
                    RecommendAttractionEntity newAttraction = new RecommendAttractionEntity(postDto, recommendId);
                    attractionRepository.save(newAttraction);
                } else {
                    attractionService.patchRecommendAttraction(dto.getAttraction(), recommendId, attractionEntity.getAttractionId(), userId);
                }
            } else {
                if (attractionEntity != null) {
                    attractionService.deleteRecommendAttraction(recommendId, attractionEntity.getAttractionId(), userId);
                }
            }

            if (dto.getFood() != null) {
                if (foodEntity == null) {
                    PostRecommendFoodRequestDto postDto = new PostRecommendFoodRequestDto();
                    postDto.setFoodName(dto.getFood().getFoodName());
                    postDto.setFoodContent(dto.getFood().getFoodContent());
                    RecommendFoodEntity newFood = new RecommendFoodEntity(postDto, recommendId);
                    foodRepository.save(newFood);
                } else {
                    foodService.patchRecommendFood(dto.getFood(), recommendId, foodEntity.getFoodId(), userId);
                }
            } else {
                if (foodEntity != null) {
                    foodService.deleteRecommendFood(recommendId, foodEntity.getFoodId(), userId);
                }
            }

            if (dto.getMission() != null) {
                if (missionEntity == null) {
                    PostRecommendMissionRequestDto postMissionDto = new PostRecommendMissionRequestDto();
                    postMissionDto.setMissionName(dto.getMission().getMissionName());
                    postMissionDto.setMissionContent(dto.getMission().getMissionContent());
                    RecommendMissionEntity newMission = new RecommendMissionEntity(postMissionDto, recommendId);
                    missionRepository.save(newMission);
                } else {
                    missionService.patchRecommendMission(dto.getMission(), recommendId, missionEntity.getMissionId(), userId);
                }
            } else {
                if (missionEntity != null) {
                    missionService.deleteRecommendMission(recommendId, missionEntity.getMissionId(), userId);
                }
            }

            if (dto.getImages() != null) {
                for (PatchRecommendImageRequestDto imageDto : dto.getImages()) {
                    for (RecommendImageEntity imageEntity : imageEntities) {
                        imageService.patchRecommendImage(imageDto, recommendId, imageEntity.getImageId(), userId);
                    }
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
    public ResponseEntity<? super GetRecommendPostResponseDto> getRecommendPost(Integer recommendId) {
    
        RecommendPostEntity recommendPostEntity = null;
        RecommendAttractionEntity recommendAttractionEntity = null;
        RecommendFoodEntity recommendFoodEntity = null;
        RecommendMissionEntity recommendMissionEntity = null;
        List<RecommendImageEntity> recommendImageEntities = new ArrayList<>();
    
        try {

            recommendPostEntity = postRepository.findByRecommendId(recommendId);
            if (recommendPostEntity == null) return ResponseDto.noExistRecommendPost();
    
            recommendAttractionEntity = attractionRepository.findByRecommendId(recommendId);
            recommendFoodEntity = foodRepository.findByRecommendId(recommendId);
            recommendMissionEntity = missionRepository.findByRecommendId(recommendId);
            recommendImageEntities = imageRepository.findByRecommendIdOrderByImageOrderAsc(recommendId);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return GetRecommendPostResponseDto.success(recommendPostEntity, recommendAttractionEntity, recommendFoodEntity, recommendMissionEntity, recommendImageEntities);
    }

    @Override
    public ResponseEntity<? super GetRecommendPostListResponseDto> getRecommendPosts() {

        List<RecommendPostEntity> postEntities = new ArrayList<>();
        List<RecommendAttractionEntity> attractionEntities = new ArrayList<>();
        List<RecommendFoodEntity> foodEntities = new ArrayList<>();
        List<RecommendMissionEntity> missionEntities = new ArrayList<>();
        List<List<RecommendImageEntity>> imageEntities = new ArrayList<>();

        try {

            postEntities = postRepository.findAllByOrderByRecommendIdDesc();

            for (RecommendPostEntity postEntity : postEntities) {
                RecommendAttractionEntity attraction = attractionRepository.findByRecommendId(postEntity.getRecommendId());
                RecommendFoodEntity food = foodRepository.findByRecommendId(postEntity.getRecommendId());
                RecommendMissionEntity mission = missionRepository.findByRecommendId(postEntity.getRecommendId());
                List<RecommendImageEntity> images = imageRepository.findByRecommendIdOrderByImageOrderAsc(postEntity.getRecommendId());

                attractionEntities.add(attraction);
                foodEntities.add(food);
                missionEntities.add(mission);
                imageEntities.add(images); 
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRecommendPostListResponseDto.success(postEntities, attractionEntities, foodEntities, missionEntities, imageEntities);
    }

}