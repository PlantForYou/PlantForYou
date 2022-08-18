package com.be1.plant4you.plant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class PlantQuesOption {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plant_ques_option_id")
    private Long id;

    @Column(name = "ques_option", nullable = false)
    private String option;

    @Column(nullable = false)
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_ques_id")
    private PlantQues plantQues;
}
