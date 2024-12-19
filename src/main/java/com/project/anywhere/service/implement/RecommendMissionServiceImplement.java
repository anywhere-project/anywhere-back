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
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.RecommendImageRepository;
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
    private final RecommendImageRepository imageRepository;
    private final RecommendMissionRepository missionRepository;

    @Override
    public ResponseEntity<ResponseDto> postRecommendMission(PostRecommendMissionRequestDto dto, Integer recommendId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendMissionEntity missionEntity = new RecommendMissionEntity(dto, recommendId);
            missionRepository.save(missionEntity);

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
    public ResponseEntity<? super GetRecommendMissionPostResponseDto> getRecommendMissionPost(Integer recommendId) {
        RecommendPostEntity postEntity = null;
        List<RecommendMissionEntity> missionEntities = new ArrayList<>();
        List<RecommendImageEntity> imageEntities = new ArrayList<>();
    
        try {

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost) return ResponseDto.noExistRecommendPost();

            postEntity = postRepository.findByRecommendId(recommendId);
            missionEntities = missionRepository.findByRecommendId(recommendId);
            imageEntities = imageRepository.findByRecommendIdOrderByImageOrderAsc(recommendId);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRecommendMissionPostResponseDto.success(postEntity, missionEntities, imageEntities);
    }

    @Override
    public ResponseEntity<? super GetRecommendMissionListResponseDto> getRecommendMissionPosts() {
        List<RecommendPostEntity> postEntities = new ArrayList<>();
        List<RecommendMissionEntity> missionEntities = new ArrayList<>();
        List<RecommendImageEntity> imageEntities = new ArrayList<>();
        
        try {
            postEntities = postRepository.findAll();
            
            for (RecommendPostEntity postEntity : postEntities) {
                List<RecommendMissionEntity> missions = missionRepository.findByRecommendId(postEntity.getRecommendId());
                missionEntities.addAll(missions);
                
                List<RecommendImageEntity> images = imageRepository.findByRecommendIdOrderByImageOrderAsc(postEntity.getRecommendId());
                imageEntities.addAll(images);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetRecommendMissionListResponseDto.success(postEntities, missionEntities, imageEntities);
    }    

}
