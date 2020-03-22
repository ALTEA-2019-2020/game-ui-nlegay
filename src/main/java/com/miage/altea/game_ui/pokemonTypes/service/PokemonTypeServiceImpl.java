package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;
    private Retry retry = Retry.ofDefaults("pokemon-types");

    @Cacheable("list-pokemon-types")
    public List<PokemonType> listPokemonsTypes() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, String.valueOf(LocaleContextHolder.getLocale()));
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<PokemonType[]> response = this.retry.executeSupplier( () ->
                restTemplate.exchange(pokemonServiceUrl+"pokemon-types/", HttpMethod.GET, entity, PokemonType[].class) );
        return List.of(response.getBody());
    }

    @Override
    @Cacheable("pokemon-types")
    public PokemonType getPokemonType(int id) {
        // j'ai utilisé la librairie resilience4j pour le retry
        return this.retry.executeSupplier( () ->
                restTemplate.getForObject(pokemonServiceUrl+"pokemon-types/{id}", PokemonType.class, id) );
    }

    @Autowired
    @Qualifier("restTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
