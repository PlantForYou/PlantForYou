package com.be1.plant4you.plant.repository;

import com.be1.plant4you.plant.domain.PlantQuesOption;
import com.be1.plant4you.plant.repository.custom.PlantQuesOptionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantQuesOptionRepository extends JpaRepository<PlantQuesOption, Long>, PlantQuesOptionRepositoryCustom {
}
