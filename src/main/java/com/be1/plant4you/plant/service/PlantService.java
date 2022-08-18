package com.be1.plant4you.plant.service;

import com.be1.plant4you.common.exception.CustomException;
import com.be1.plant4you.plant.domain.PlantQues;
import com.be1.plant4you.plant.dto.response.PlantDictListResponse;
import com.be1.plant4you.plant.dto.response.PlantDictResponse;
import com.be1.plant4you.plant.dto.response.PlantQuesResponse;
import com.be1.plant4you.plant.dto.response.PlantScoreResponse;
import com.be1.plant4you.plant.repository.PlantDictRepository;
import com.be1.plant4you.plant.repository.PlantQuesOptionRepository;
import com.be1.plant4you.plant.repository.PlantQuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.be1.plant4you.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantDictRepository plantDictRepository;
    private final PlantQuesRepository plantQuesRepository;
    private final PlantQuesOptionRepository plantQuesOptionRepository;

    public PlantScoreResponse getPlantScoreResult(Long sunLevel, Long hardLevel, Boolean isEdible, Boolean isToxic, Long sizeLevel) {
        //식용 true, 독성 true 경우 -> 독성 false 변경
        if (isEdible && isToxic) {
            isToxic = false;
        }
        return plantDictRepository.findByPlantScore(sunLevel, hardLevel, isEdible, isToxic, sizeLevel);
    }

    public List<PlantDictListResponse> getPlantDictList() {
        return plantDictRepository.findAllDto();
    }

    public PlantDictResponse getPlantDict(Long plantDictId) {
        plantDictRepository.findById(plantDictId).orElseThrow(() -> new CustomException(NOT_FOUND_PLANT));
        return plantDictRepository.findDtoById(plantDictId);
    }

    public PlantQuesResponse getPlantQues(Long plantQuesId) {
        PlantQues plantQues = plantQuesRepository.findById(plantQuesId).orElseThrow(RuntimeException::new);
        List<PlantQuesResponse.Option> optionList = plantQuesOptionRepository.findAllByPlantQuesId(plantQuesId);

        return PlantQuesResponse.builder()
                .plantQuesId(plantQuesId)
                .question(plantQues.getQuestion())
                .optionList(optionList)
                .build();
    }
}
