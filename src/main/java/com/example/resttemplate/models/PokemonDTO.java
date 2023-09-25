package com.example.resttemplate.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class PokemonDTO {
    private String name;
    private long nationalPokedexNumber;
    private List<String> types = new ArrayList<>();
    private List<String> abilities = new ArrayList<>();
    private String pokemonImageUrl ;
    private Integer size;
    private Integer weight;
}
