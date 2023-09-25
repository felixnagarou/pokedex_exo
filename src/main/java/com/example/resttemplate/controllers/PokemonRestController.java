package com.example.resttemplate.controllers;

import com.example.resttemplate.models.PokemonDTO;
import com.example.resttemplate.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class PokemonRestController {
    private final PokemonService pokemonService;

    @GetMapping("/pokemonForm/ditto")
    public PokemonDTO getDefaultPokemonEntry(){
        return pokemonService.getPokemonDataJsonNode("ditto");
    }

    @GetMapping("/pokemonForm/{name}")
    public PokemonDTO getPokemonByName(@PathVariable("name") String name){
        return pokemonService.getPokemonDataJsonNode(name);
    }



   //@PostMapping("/{name}")
   //public PokemonDTO requestPokemonDataByName(@PathVariable("name")String name){
   //    PokemonDTO pokemonDTO = pokemonService.getPokemonDataJsonNode(name);
   //    if (pokemonDTO != null) {

   //    }

   //}


}
