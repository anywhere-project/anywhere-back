package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PatchRecommendMissionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendMissionRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.RecommendMissionRepository;
import com.project.anywhere.repository.RecommendPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendMissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendMissionServiceImplement implements RecommendMissionService {

    private final UserRepository userRepository;
    private final RecommendPostRepository postRepository;
    private final RecommendMissionRepository missionRepository;

    @Override
    public ResponseEntity<ResponseDto> postRecommendMission(PostRecommendMissionRequestDto dto, Integer recommendId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost) return ResponseDto.noExistRecommendPost();

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

            missionEntity.patch(dto, missionId);
            missionRepository.save(missionEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
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
}
