package com.example.t1_haeun_hekim4;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.t1_haeun_hekim4.databinding.CustomRowLayoutBinding;
import com.example.t1_haeun_hekim4.db.PokemonSingleton;
import com.example.t1_haeun_hekim4.models.Pokemon;

import java.util.List;
import java.util.Map;

public class PokemonObjectAdapter extends ArrayAdapter {
    private List<Pokemon> pokemonList;
    PokemonSingleton pokemonManager = PokemonSingleton.getInstance();
    private SharedPreferences sp;

    public PokemonObjectAdapter(@NonNull Context context, List<Pokemon> pokemonList, SharedPreferences sp)
    {
        super(context, 0);
        this.pokemonList = pokemonList;
        this.sp = sp;
    }

    @Override
    public int getCount() {
        return pokemonList.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_row_layout, parent, false);
        }


        CustomRowLayoutBinding binding = CustomRowLayoutBinding.bind(convertView);

        Pokemon currPokemon = pokemonManager.getPokemonList().get(position);
        String stringKey = "KEY_" + currPokemon.getNumber();

        String key = this.sp.getString(stringKey, null);

        if(key == null){
            convertView.setBackgroundColor(Color.WHITE);
        } else{
            convertView.setBackgroundColor(Color.YELLOW);
        }

        // @TODO: code to update the ui
        binding.tvPokemonName.setText(currPokemon.getName());
        binding.tvPokemonRecord.setText("Wins: " + pokemonManager.getPokemonList().get(position).getWins() + " - " + pokemonManager.getPokemonList().get(position).getLoss());


        // Return the completed view to render on screen
        return convertView;
    }

}
