package com.example.pokemontestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.pokemontestapplication.data.PokemonDB;
import com.example.pokemontestapplication.databinding.ActivitySecandBinding;
import com.example.pokemontestapplication.model.Pokemon;
import com.example.pokemontestapplication.ui.PokemonAdapter;

import java.util.ArrayList;

public class SecandActivity extends AppCompatActivity {

    private static final String TAG = "SecandActivity";
    ActivitySecandBinding secandBinding;
    PokemonDB pokemonDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secandBinding = ActivitySecandBinding.inflate(getLayoutInflater());
        setContentView(secandBinding.getRoot());
        pokemonDB = PokemonDB.getInstanse(this);

        ArrayList<Pokemon> pokemons = getDataFromRoom();
        Log.d(TAG,"MEDMJS => size : "+pokemons.size());
        Log.d(TAG,"MEDMJS => name : "+pokemons.get(0).getName());
        Log.d(TAG,"MEDMJS => url : "+pokemons.get(0).getUrl());


        fillRecycleView(pokemons);


    }

    public ArrayList<Pokemon> getDataFromRoom() {
        ExtendThread extendThread =new ExtendThread();
        extendThread.start();

        ArrayList<Pokemon> pokemons =extendThread.getPokemons();
        return pokemons;

    }


    public void fillRecycleView(ArrayList<Pokemon> pokemons) {
        PokemonAdapter adapter = new PokemonAdapter();
        adapter.setList(pokemons);
        secandBinding.secandRv.setLayoutManager(new LinearLayoutManager(this));
        secandBinding.secandRv.setAdapter(adapter);
    }

    class ExtendThread extends Thread{
        ArrayList<Pokemon> pokemons;

        public ArrayList<Pokemon> getPokemons() {
            return pokemons;
        }

        @Override
        public void run() {
            pokemons = (ArrayList<Pokemon>) pokemonDB.pokemonDao().getPokemons();
        }
    }
}