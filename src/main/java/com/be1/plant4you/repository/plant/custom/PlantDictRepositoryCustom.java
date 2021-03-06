package com.be1.plant4you.repository.plant.custom;

import com.be1.plant4you.dto.response.plant.PlantDictListResponse;
import com.be1.plant4you.dto.response.plant.PlantDictResponse;
import com.be1.plant4you.dto.response.plant.PlantScoreResponse;

import java.util.List;

public interface PlantDictRepositoryCustom {

    PlantScoreResponse findByPlantScore(Byte sunLevel, Byte hardLevel, Boolean isEdible, Boolean isToxic, Byte sizeLevel);
    List<PlantDictListResponse> findAllDto();
    PlantDictResponse findDtoById(Long plantDictId);
}
