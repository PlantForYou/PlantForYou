package com.be1.plant4you.controller.plant;

import com.be1.plant4you.dto.request.plant.PlantScoreRequest;
import com.be1.plant4you.dto.response.plant.PlantDictListResponse;
import com.be1.plant4you.dto.response.plant.PlantDictResponse;
import com.be1.plant4you.dto.response.plant.PlantScoreResponse;
import com.be1.plant4you.service.plant.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/plants")
@RestController
public class PlantController {

    private final PlantService plantService;

    /**
     * 식물 mbti 결과 반환 (인증되지 않은 이용자도 요청 가능)
     */
    @GetMapping("/mbti")
    public PlantScoreResponse getPlantScoreResult(@RequestBody PlantScoreRequest plantScoreRequest) {
        return plantService.getPlantScoreResult(plantScoreRequest);
    }

    /**
     * 식물사전 리스트 조회
     */
    @GetMapping
    public List<PlantDictListResponse> getPlantDictList() {
        return plantService.getPlantDictList();
    }

    /**
     * 식물사전 상세내용 조회
     */
    @GetMapping("/{plantDictId}")
    public PlantDictResponse getPlantDict(@PathVariable Long plantDictId) {
        return plantService.getPlantDict(plantDictId);
    }
}
