package com.be1.plant4you.plant.repository.custom;

import com.be1.plant4you.plant.dto.response.PlantQuesResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.be1.plant4you.plant.domain.QPlantQuesOption.*;

@RequiredArgsConstructor
public class PlantQuesOptionRepositoryImpl implements PlantQuesOptionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PlantQuesResponse.Option> findAllByPlantQuesId(Long plantQuesId) {
        return queryFactory
                .select(Projections.constructor(PlantQuesResponse.Option.class,
                        plantQuesOption.id,
                        plantQuesOption.option,
                        plantQuesOption.score
                ))
                .from(plantQuesOption)
                .where(plantQuesOption.plantQues.id.eq(plantQuesId))
                .fetch();
    }
}
