package com.example.t1_haeun_hekim4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.t1_haeun_hekim4.databinding.ActivityMainBinding;
import com.example.t1_haeun_hekim4.db.PokemonSingleton;
import com.example.t1_haeun_hekim4.models.Pokemon;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PokemonSingleton pokemonManager;
    private PokemonObjectAdapter pokemonAdopter;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pokemonManager = PokemonSingleton.getInstance();

        this.sp = getSharedPreferences("MY-SP", Context.MODE_PRIVATE);

        pokemonAdopter = new PokemonObjectAdapter(this, pokemonManager.getPokemonList(), sp);
        binding.lvPokemon.setAdapter(pokemonAdopter);

        binding.lvPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pokemon currPokemon = pokemonManager.getPokemonList().get(i);

                Intent intent = new Intent(MainActivity.this, Screen2Activity.class);
                intent.putExtra("EXTRA_SELECTED_POKEMON_ID", currPokemon.getNumber());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pokemonManager = PokemonSingleton.getInstance();
        pokemonAdopter = new PokemonObjectAdapter(this, pokemonManager.getPokemonList(), sp);
        binding.lvPokemon.setAdapter(pokemonAdopter);
    }
}