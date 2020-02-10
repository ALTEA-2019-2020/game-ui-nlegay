package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    public List<Trainer> listTrainers() {
        var result = restTemplate.getForObject(pokemonServiceUrl+"trainers/", Trainer[].class);
        return List.of(result);
    }

    @Override
    public Trainer getTrainer(String name) {
        var result = restTemplate.getForObject(pokemonServiceUrl + "trainers/" + name, Trainer.class);
        return result;
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
