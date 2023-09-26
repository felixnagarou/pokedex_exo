package com.example.resttemplate.services;

import com.example.resttemplate.models.PokemonDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class PokemonService {

    private final RestTemplateBuilder builder;

    public PokemonDTO getPokemonDataJsonNode(String name){

        RestTemplate resttemplate = builder.build();
        ResponseEntity<JsonNode> entity = resttemplate.getForEntity("api/v2/pokemon/" + name, JsonNode.class);

         List<String> typeNames = new ArrayList<>();
         List<String> abilitiesNames = new ArrayList<>();

         int id = entity.getBody().get("id").asInt();
         //fetch ability List
        entity.getBody().findPath("abilities").elements().forEachRemaining(t -> {
            abilitiesNames.add(t.findPath("ability").get("name").asText());
        });
        //fetch type List
        entity.getBody().findPath("types").elements().forEachRemaining(t -> {
            typeNames.add(t.findPath("type").get("name").asText());
        });
     //  entity.getBody().findPath("sprites").elements().forEachRemaining(t -> {
     //      imgUrls.add(t.get("front_default").asText());
     //  });

        PokemonDTO pokemonData = PokemonDTO.builder()
                .name(entity.getBody().get("name").asText())
                .nationalPokedexNumber(id)
                .types(typeNames)
                .abilities(abilitiesNames)
                .pokemonImageUrl("http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + String.format("%03d", id) +".png")
                .size(entity.getBody().get("height").asInt())
                .weight(entity.getBody().get("weight").asInt())
                .build();

        return pokemonData;
    }
}
