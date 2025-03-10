package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PatchRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendMissionListResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendMissionPostResponseDto;
import com.project.anywhere.entity.MissionImageEntity;
import com.project.anywhere.entity.MissionLikeEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.MissionImageRepository;
import com.project.anywhere.repository.MissionLikeRepository;
import com.project.anywhere.repository.RecommendMissionRepository;
import com.project.anywhere.repository.RecommendPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendMissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendMissionServiceImplement implements RecommendMissionService {

    private final UserRepository userRepository;
    private final RecommendPostRepository postRepository;
    private final RecommendMissionRepository missionRepository;
    private final MissionImageRepository imageRepository;
    private final MissionLikeRepository likeRepository;

    @Override
    public ResponseEntity<ResponseDto> postRecommendMission(PostRecommendMissionRequestDto dto, Integer recommendId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendMissionEntity missionEntity = new RecommendMissionEntity(dto, recommendId);
            missionRepository.save(missionEntity);

            for (String image: dto.getImages()) {
                MissionImageEntity imageEntity = new MissionImageEntity(image, missionEntity.getMissionId());
                imageRepository.save(imageEntity);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchRecommendMission(PatchRecommendMissionRequestDto dto, Integer recommendId, Integer missionId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();
            if (!postEntity.getRecommendWriter().equals(userId)) return ResponseDto.noPermission();

            RecommendMissionEntity missionEntity = missionRepository.findByMissionId(missionId);
            if (missionEntity == null) return ResponseDto.noExistRecommendMission();

            missionEntity.patch(dto);
            missionRepository.save(missionEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteRecommendMission(Integer recommendId, Integer missionId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();
            if (!postEntity.getRecommendWriter().equals(userId)) return ResponseDto.noPermission();

            RecommendMissionEntity missionEntity = missionRepository.findByMissionId(missionId);
            if (missionEntity == null) return ResponseDto.noExistRecommendMission();

            missionRepository.deleteByMissionId(missionId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetRecommendMissionPostResponseDto> getRecommendMission(Integer recommendId) {
        List<RecommendMissionEntity> missionEntities = new ArrayList<>();
        List<MissionImageEntity> imageEntities = new ArrayList<>();
        List<MissionLikeEntity> likeEntities = new ArrayList<>();

        try {

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost)
                return ResponseDto.noExistRecommendPost();

            missionEntities = missionRepository.findByRecommendId(recommendId);
            imageEntities = imageRepository.findAll();
            likeEntities = likeRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRecommendMissionPostResponseDto.success(missionEntities, imageEntities, likeEntities);
    }

    @Override
    public ResponseEntity<? super GetRecommendMissionListResponseDto> getRecommendMissions() {
        List<RecommendMissionEntity> missionEntities = new ArrayList<>();
        List<MissionImageEntity> imageEntities = new ArrayList<>();
        List<MissionLikeEntity> likeEntities = new ArrayList<>();

        try {

            missionEntities = missionRepository.findAll();
            imageEntities = imageRepository.findAll();
            likeEntities = likeRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRecommendMissionListResponseDto.success(missionEntities, imageEntities, likeEntities);
    }

}
