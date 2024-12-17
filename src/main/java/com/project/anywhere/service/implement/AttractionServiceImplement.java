package com.project.anywhere.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.common.object.Attractions;
import com.project.anywhere.dto.response.attraction.GetAttractionResponseDto;
import com.project.anywhere.entity.AttractionsEntity;
import com.project.anywhere.repository.AttractionRepository;
import com.project.anywhere.service.AttractionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionServiceImplement implements AttractionService {

    private final AttractionRepository attractionRepository;

    @Override
    public ResponseEntity<? super GetAttractionResponseDto> getAttraction() {

        List<AttractionsEntity> attractionsEntities = null;

                try {

                    attractionsEntities = attractionRepository.findAll();
            if (attractionsEntities == null || attractionsEntities.isEmpty())
                return GetAttractionResponseDto.noExistAttractionId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return GetAttractionResponseDto.databaseError();
        }
        List<Attractions> attractions = attractionsEntities.stream()
                .map(attractionsEntity -> new Attractions(
                    attractionsEntity.getAttractionId(), 
                    attractionsEntity.getAreaId(),
                    attractionsEntity.getAttractionName(),
                    attractionsEntity.getAttractionAddress()
                    ))
                .collect(Collectors.toList());

        return GetAttractionResponseDto.success(attractions);

    }
    
}
