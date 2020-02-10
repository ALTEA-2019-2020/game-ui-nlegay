package com.miage.altea.game_ui.trainers.bo;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;

import java.util.List;

public class Trainer {

    private String name;
    private List<PokemonType> team;

    public Trainer() {
    }

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonType> getTeam() {
        return team;
    }

    public void setTeam(List<PokemonType> team) {
        this.team = team;
    }

}
