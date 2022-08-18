package com.be1.plant4you.plant.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "식물추천 질문 조회 시 반환하는 DTO")
@Getter @Builder
@AllArgsConstructor
public class PlantQuesResponse {

    @Schema(description = "식물추천 질문 ID", example = "1")
    private Long plantQuesId;

    @Schema(description = "식물추천 질문", example = "1. 당신의 소중한 반려식물, 두는곳은 어디에?")
    private String question;

    private List<Option> optionList;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Option {

        @Schema(description = "식물추천 질문 선택지 ID", example = "1")
        private Long plantQuesOptionId;

        @Schema(description = "식물추천 질문 선택지", example = "a. 햇볕 잘드는 창가")
        private String option;

        @Schema(description = "식물추천 질문 선택지 매핑 점수", example = "2")
        private int score;
    }
}
