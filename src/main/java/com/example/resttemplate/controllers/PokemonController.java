package com.example.resttemplate.controllers;

import com.example.resttemplate.models.PokemonDTO;
import com.example.resttemplate.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pokemonInfo")
public class PokemonController {
    private final PokemonService pokemonService;

   @GetMapping
   public String defaultForm(Model model){
        model.addAttribute("pokemon", pokemonService.getPokemonDataJsonNode("ditto"));
       return "/pokemonInfo/pokemonForm";
   }

    @GetMapping("/{name}")
    public String getPokemonByIdOrName(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("pokemon", pokemonService.getPokemonDataJsonNode(name));
        return "pokemonInfo/pokemonForm";
    }

   @PostMapping("/search")
   public String searchPokemon(Model model, String name){
       model.addAttribute("name", name);
       model.addAttribute("pokemon", pokemonService.getPokemonDataJsonNode(name.toLowerCase()));
       return "pokemonInfo/pokemonForm";
   }

}
