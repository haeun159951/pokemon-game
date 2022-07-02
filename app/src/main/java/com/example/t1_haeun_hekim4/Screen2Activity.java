package com.example.t1_haeun_hekim4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.t1_haeun_hekim4.databinding.ActivityScreen2Binding;
import com.example.t1_haeun_hekim4.db.PokemonSingleton;
import com.example.t1_haeun_hekim4.models.Pokemon;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class Screen2Activity extends AppCompatActivity {

    private ActivityScreen2Binding binding;
    private SharedPreferences sp;
    private PokemonSingleton pokemonManager;

    private int win = 0;
    private int loss = 0;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreen2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pokemonManager = PokemonSingleton.getInstance();
        this.sp = getSharedPreferences("MY-SP", Context.MODE_PRIVATE);
        intent = getIntent();

        if (intent != null) {
            int idFromScreen1 = intent.getIntExtra("EXTRA_SELECTED_POKEMON_ID", -1);
            Pokemon currPokemon = pokemonManager.getPokemonById(idFromScreen1);
            binding.tvPokemonName.setText("Name: " + currPokemon.getName());
            binding.tvPokemonNumber.setText("Pokedex Number: " + String.valueOf(currPokemon.getNumber()));
            int imageid = getResources().getIdentifier(currPokemon.getName().toLowerCase(), "drawable",
                    getPackageName());
            binding.ivImage.setImageResource(imageid);

            binding.btnFightComputer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pokemon player = pokemonManager.getPokemonById(idFromScreen1);
                    Random rn = new Random();
                    int firstRandomNumber = rn.nextInt(5) + 1;
                    int secondRandomNumber = rn.nextInt(5) + 1;
                    if (firstRandomNumber > secondRandomNumber) {
                        // player win
                        win += 1;
                        player.setWins(win);
                        Snackbar.make(binding.getRoot(), "Player: " + firstRandomNumber + " Computer: " + secondRandomNumber + " Winner: Player"  , Snackbar.LENGTH_SHORT).show();
                    } else if (firstRandomNumber < secondRandomNumber) {
                        // computer win
                        loss += 1;
                        player.setLoss(loss);
                        Snackbar.make(binding.getRoot(), "Player: " + firstRandomNumber + " Computer: " + secondRandomNumber + " Winner: Computer"  , Snackbar.LENGTH_SHORT).show();
                    } else {
                        // computer win
                        loss += 1;
                        player.setLoss(loss);
                        Snackbar.make(binding.getRoot(), "Player: " + firstRandomNumber + " Computer: " + secondRandomNumber + " Winner: Computer"  , Snackbar.LENGTH_SHORT).show();
                    }

                    Log.d("ABC", "Result: " + player.getWins() +  " " + player.getLoss());
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.screen2_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle the different menu options that were clicked
        SharedPreferences.Editor spEditor = sp.edit();
        intent = getIntent();
        int idFromScreen1 = intent.getIntExtra("EXTRA_SELECTED_POKEMON_ID", -1);
        Log.d("ABC", "id: " + idFromScreen1);
        Pokemon currPokemon = pokemonManager.getPokemonById(idFromScreen1);
        Log.d("ABC", "currPokemon: " + currPokemon.getNumber());
        String key = "KEY_" + currPokemon.getNumber();

        switch (item.getItemId()) {
            case R.id.menuFavourite:
                spEditor.putString(key, "");
                spEditor.apply();
                Snackbar.make(binding.getRoot(), "Favorited!", Snackbar.LENGTH_SHORT).show();
                return true;

            case R.id.menuReset:
                currPokemon.setLoss(0);
                currPokemon.setWins(0);
                spEditor.remove(key);
                spEditor.commit();
                Snackbar.make(binding.getRoot(), "Pokemon reset!", Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
