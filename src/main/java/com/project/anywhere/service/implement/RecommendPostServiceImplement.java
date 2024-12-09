package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.common.object.RecommendPost;
import com.project.anywhere.dto.request.recommend.PatchRecommendPostRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendImageRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendPostListResponseDto;
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
    public ResponseEntity<? super GetRecommendPostListResponseDto> getRecommendPosts() {

        List<RecommendPostEntity> postEntities = postRepository.findAllByOrderByRecommendIdDesc();
        List<RecommendPost> recommendPosts = new ArrayList<>();

        try {

            for (RecommendPostEntity postEntity : postEntities) {
                RecommendAttractionEntity attractionEntity = attractionRepository.findByRecommendId(postEntity.getRecommendId());
                RecommendFoodEntity foodEntity = foodRepository.findByRecommendId(postEntity.getRecommendId());
                RecommendMissionEntity missionEntity = missionRepository.findByRecommendId(postEntity.getRecommendId());
                List<RecommendImageEntity> imageEntities = imageRepository.findByRecommendIdOrderByImageOrderAsc(postEntity.getRecommendId());

                RecommendPost recommendPost = new RecommendPost(postEntity, attractionEntity, foodEntity, missionEntity, imageEntities);
                recommendPosts.add(recommendPost);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    return GetRecommendPostListResponseDto.success(recommendPosts);
    }

}
