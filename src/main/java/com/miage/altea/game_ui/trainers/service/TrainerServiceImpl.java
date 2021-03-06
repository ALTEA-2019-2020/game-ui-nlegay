package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.trainers.bo.Pokemon;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String trainerServiceUrl;
    private String pokemonServiceUrl;

    @Cacheable("trainers")
    public List<Trainer> listTrainers() {
        var result = restTemplate.getForObject(trainerServiceUrl+"trainers/", Trainer[].class);
        return List.of(result);
    }

    @Override
    public Trainer getTrainer(String name) {
        var result = restTemplate.getForObject(trainerServiceUrl + "trainers/" + name, Trainer.class);

        List<Pokemon> team = new ArrayList<>();
        for( var pokemonType : result.getTeam() ) {
            team.add( new Pokemon(pokemonType.getPokemonTypeId(), pokemonType.getLevel(), restTemplate.getForObject(pokemonServiceUrl+"pokemon-types/" + pokemonType.getPokemonTypeId(), PokemonType.class)) );
        }
        result.setTeam(team);

        return result;
    }

    @Override
    public List<Trainer> getAllTrainers(String namePrincipal) {
        // get All Trainers expect the trainer parameter
        List<Trainer> allTrainersExceptParameterTrainer = new ArrayList<>();
        List<Trainer> allTrainers = listTrainers();
        for ( Trainer t : allTrainers ) {
            if (!t.getName().equals(namePrincipal)){
                allTrainersExceptParameterTrainer.add( getTrainer(t.getName()) );
            }
        }
        return allTrainersExceptParameterTrainer;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }

    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }

}
