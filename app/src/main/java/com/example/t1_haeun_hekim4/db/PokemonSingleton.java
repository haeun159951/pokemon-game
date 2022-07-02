package com.example.t1_haeun_hekim4.db;

import com.example.t1_haeun_hekim4.models.Pokemon;

import java.util.ArrayList;

public class PokemonSingleton {

    // boilerplate code
    private PokemonSingleton(){
        loadPokemonDataSet();
    }

    // template code for creating a singleton
    private static PokemonSingleton instance = null;
    public static PokemonSingleton getInstance(){
        if(instance == null){
            instance = new PokemonSingleton();
        }
        return  instance;
    }

    // properties
    // any data you want the singleton to manage - list of pokemon
    private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

    // methods - helper function

    private void loadPokemonDataSet(){
        this.pokemonList.add(new Pokemon(12, "Caterpie"));
        this.pokemonList.add(new Pokemon(19, "Rattata"));
        this.pokemonList.add(new Pokemon(25, "Pikachu"));
        this.pokemonList.add(new Pokemon(147, "Dratini"));
    }

    // getter
    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public Pokemon getPokemonById(int pokedexNumber){
        for(int i =0; i< pokemonList.size(); i++){
            Pokemon currPokemon = this.pokemonList.get(i);
            if(currPokemon.getNumber() == pokedexNumber){
                return currPokemon;
            }
        }
        return null;
    }

}