package com.miage.altea.game_ui.trainers.bo;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;

public class Pokemon {

    private int pokemonTypeId;

    private int level;

    private PokemonType pokemonType;

    public Pokemon() {
    }

    public Pokemon(int pokemonTypeId, int level, PokemonType pokemonType) {
        this.pokemonTypeId = pokemonTypeId;
        this.level = level;
        this.pokemonType = pokemonType;
    }

    public PokemonType getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(PokemonType pokemonType) {
        this.pokemonType = pokemonType;
    }

    public int getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(int pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
