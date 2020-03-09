package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.trainers.bo.Trainer;

import java.util.List;

public interface TrainerService {

    List<Trainer> listTrainers();

    Trainer getTrainer(String name);

    List<Trainer> getAllTrainers(String namePrincipal);
}
