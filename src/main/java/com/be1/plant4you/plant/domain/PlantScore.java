package com.be1.plant4you.plant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class PlantScore {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plant_score_id")
    private Long id;

    @Column(nullable = false)
    private Long sunLevel;

    @Column(nullable = false)
    private Long hardLevel;

    @Column(nullable = false)
    private Boolean isEdible;

    @Column(nullable = false)
    private Boolean isToxic;

    @Column(nullable = false)
    private Long sizeLevel;
}
