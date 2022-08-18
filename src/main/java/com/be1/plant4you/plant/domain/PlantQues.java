package com.be1.plant4you.plant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class PlantQues {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plant_ques_id")
    private Long id;

    @Column(nullable = false)
    private String question;

    @Builder.Default
    @OneToMany(mappedBy = "plantQues")
    private List<PlantQuesOption> optionList = new ArrayList<>();
}
