package com.project.anywhere.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.common.object.Areas;
import com.project.anywhere.dto.response.area.GetAreaResponseDto;
import com.project.anywhere.entity.AreasEntity;
import com.project.anywhere.repository.AreaRepository;
import com.project.anywhere.service.AreaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaServiceImplement implements AreaService {

    private final AreaRepository areaRepository;

    @Override
    public ResponseEntity<? super GetAreaResponseDto> getArea() {

        List<AreasEntity> areasEntities = null;

        try {

            areasEntities = areaRepository.findAll();
            if (areasEntities == null || areasEntities.isEmpty())
                return GetAreaResponseDto.noExistAreaId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return GetAreaResponseDto.databaseError();
        }

        List<Areas> areas = areasEntities.stream()
                .map(areaEntity -> new Areas(areaEntity.getAreaId(), areaEntity.getAreaName()))
                .collect(Collectors.toList());

        return GetAreaResponseDto.success(areas);
    }

}