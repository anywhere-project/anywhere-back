package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.MissionLikeEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.MissionLikeRepository;
import com.project.anywhere.repository.RecommendMissionRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.MissionLikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionLikeServiceImplement implements MissionLikeService {

    private final UserRepository userRepository;
    private final MissionLikeRepository likeRepository;
    private final RecommendMissionRepository missionRepository;

    @Override
    public ResponseEntity<ResponseDto> missionLike(Integer missionId, String userId) {
        
        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            RecommendMissionEntity missionEntity = missionRepository.findByMissionId(missionId);
            if (missionEntity == null) return ResponseDto.noExistRecommendMission();

            boolean isLiked = likeRepository.existsByUserIdAndMissionId(userId, missionId);
            if (isLiked) {
                likeRepository.deleteByUserIdAndMissionId(userId, missionId);
                missionEntity.setMissionLike(missionEntity.getMissionLike() - 1);
                missionRepository.save(missionEntity);
                return ResponseDto.likeUnclick();
            } else {
                MissionLikeEntity likeEntity = new MissionLikeEntity(userId, missionId);
                likeRepository.save(likeEntity);
                missionEntity.setMissionLike(missionEntity.getMissionLike() + 1);
                missionRepository.save(missionEntity);
                return ResponseDto.likeClick();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
