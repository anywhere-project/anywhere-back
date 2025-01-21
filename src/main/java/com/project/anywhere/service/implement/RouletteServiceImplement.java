package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.roulette.PostAreaRequestDto;
import com.project.anywhere.dto.request.roulette.PostAttractionRequestDto;
import com.project.anywhere.dto.request.roulette.PostFoodRequestDto;
import com.project.anywhere.dto.request.roulette.PostMissionRequestDto;
import com.project.anywhere.dto.request.roulette.PostRouletteRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.roulette.GetRouletteListResponseDto;
import com.project.anywhere.entity.AreasEntity;
import com.project.anywhere.entity.AttractionsEntity;
import com.project.anywhere.entity.FoodEntity;
import com.project.anywhere.entity.MissionsEntity;
import com.project.anywhere.entity.MyRandomEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.AreaRepository;
import com.project.anywhere.repository.AttractionRepository;
import com.project.anywhere.repository.FoodRepository;
import com.project.anywhere.repository.MissionRepository;
import com.project.anywhere.repository.MyRandomRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RouletteService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouletteServiceImplement implements RouletteService {

    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;
    private final FoodRepository foodRepository;
    private final MissionRepository missionRepository;
    private final AreaRepository areaRepository;
    private final MyRandomRepository randomRepository;
    
    @Override
    public ResponseEntity<ResponseDto> postArea(PostAreaRequestDto dto, String userId) {

        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            if (!usersEntity.getIsAdmin()) {
                return ResponseDto.noPermission();
            }

            AreasEntity areasEntity = new AreasEntity(dto);
            areaRepository.save(areasEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteArea(Integer areaId, String userId) {

        try {

            AreasEntity areasEntity = areaRepository.findByAreaId(areaId);
            if (areasEntity == null) return ResponseDto.noExistAreaId();

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            if (!usersEntity.getIsAdmin()) {
                return ResponseDto.noPermission();
            }

            areaRepository.deleteByAreaId(areaId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
    @Override
    public ResponseEntity<ResponseDto> postAttraction(PostAttractionRequestDto dto, Integer areaId, String userId) {

        try {

            AreasEntity areasEntity = areaRepository.findByAreaId(areaId);
            if (areasEntity == null) return ResponseDto.noExistAreaId();

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            if (!usersEntity.getIsAdmin()) {
                return ResponseDto.noPermission();
            }

            AttractionsEntity attractionsEntity = new AttractionsEntity(areaId, dto);
            attractionRepository.save(attractionsEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteAttraction(Integer areaId, Integer attractionId, String userId) {

        try {

            AreasEntity areasEntity = areaRepository.findByAreaId(areaId);
            if (areasEntity == null) return ResponseDto.noExistAreaId();

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            if (!usersEntity.getIsAdmin()) {
                return ResponseDto.noPermission();
            }

            AttractionsEntity attractionsEntity = attractionRepository.findByAttractionId(attractionId);
            if (attractionsEntity == null) return ResponseDto.noExistAttractionId();
            attractionRepository.deleteByAttractionIdAndAreaId(attractionId, areaId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postFood(PostFoodRequestDto dto, String userId) {

        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            if (!usersEntity.getIsAdmin()) {
                return ResponseDto.noPermission();
            }

            FoodEntity foodEntity = new FoodEntity(dto);
            foodRepository.save(foodEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteFood(Integer foodId, String userId) {

        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            if (!usersEntity.getIsAdmin()) {
                return ResponseDto.noPermission();
            }

            FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
            if (foodEntity == null) return ResponseDto.noExistFoodId();
            foodRepository.deleteByFoodId(foodId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postMission(PostMissionRequestDto dto, String userId) {

        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            if (!usersEntity.getIsAdmin()) {
                return ResponseDto.noPermission();
            }

            MissionsEntity missionsEntity = new MissionsEntity(dto);
            missionRepository.save(missionsEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteMission(Integer missionId, String userId) {

        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            if (!usersEntity.getIsAdmin()) {
                return ResponseDto.noPermission();
            }

            MissionsEntity missionsEntity = missionRepository.findByMissionId(missionId);
            if (missionsEntity == null) return ResponseDto.noExistMissionId(); 

            missionRepository.deleteByMissionId(missionId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postMyRandom(PostRouletteRequestDto dto, String userId) {
        
        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            MyRandomEntity myRandomEntity = new MyRandomEntity(dto);
            myRandomEntity.setUserId(userId);
            randomRepository.save(myRandomEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteMyRandom(Integer randomId, String userId) {

        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            MyRandomEntity myRandomEntity = randomRepository.findByRandomId(randomId);
            if (myRandomEntity == null) return ResponseDto.noExistMyRandom();

            randomRepository.deleteByRandomId(randomId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetRouletteListResponseDto> getMyRandomList(String userId) {
        List<MyRandomEntity> myRandomEntities = new ArrayList<>();

        try {

            myRandomEntities = randomRepository.findByOrderByRandomIdDesc();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRouletteListResponseDto.success(myRandomEntities);
    }
    
}
