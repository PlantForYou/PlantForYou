package com.be1.plant4you.plant.repository.custom;

import com.be1.plant4you.plant.dto.response.PlantDictListResponse;
import com.be1.plant4you.plant.dto.response.PlantDictResponse;
import com.be1.plant4you.plant.dto.response.PlantScoreResponse;

import java.util.List;

public interface PlantDictRepositoryCustom {

    PlantScoreResponse findByPlantScore(Long sunLevel, Long hardLevel, Boolean isEdible, Boolean isToxic, Long sizeLevel);
    List<PlantDictListResponse> findAllDto();
    PlantDictResponse findDtoById(Long plantDictId);
}
