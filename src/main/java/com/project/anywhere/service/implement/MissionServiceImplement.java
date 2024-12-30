package com.project.anywhere.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.common.object.Missions;
import com.project.anywhere.dto.response.mission.GetMissionResponseDto;
import com.project.anywhere.entity.MissionsEntity;
import com.project.anywhere.repository.MissionRepository;
import com.project.anywhere.service.MissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionServiceImplement implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    public ResponseEntity<? super GetMissionResponseDto> getMission() {

        List<MissionsEntity> missionsEntities = null;

        try {

            missionsEntities = missionRepository.findAll();
            if (missionsEntities == null || missionsEntities.isEmpty())
                return GetMissionResponseDto.noExistMissionId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return GetMissionResponseDto.databaseError();
        }

        List<Missions> missions = missionsEntities.stream()
                .map(missionsEntity -> new Missions(
                    missionsEntity.getMissionId(), 
                    missionsEntity.getMissionName(),
                    missionsEntity.getMissionContent()))
                .collect(Collectors.toList());

        return GetMissionResponseDto.success(missions);

    }
    
}
