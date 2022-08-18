package com.be1.plant4you.plant.repository.custom;

import com.be1.plant4you.plant.dto.response.PlantQuesResponse;

import java.util.List;

public interface PlantQuesOptionRepositoryCustom {

    List<PlantQuesResponse.Option> findAllByPlantQuesId(Long plantQuesId);
}
