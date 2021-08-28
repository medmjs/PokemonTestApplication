package com.example.pokemontestapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.pokemontestapplication.SecandActivity;
import com.example.pokemontestapplication.data.PokemonDB;
import com.example.pokemontestapplication.ui.PokemonViewModel;
import com.example.pokemontestapplication.ui.PokemonAdapter;
import com.example.pokemontestapplication.databinding.ActivityMainBinding;
import com.example.pokemontestapplication.model.Pokemon;
import com.example.pokemontestapplication.model.PokemonResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding mainBinding;
    PokemonViewModel pokemonViewModel;
    PokemonDB pokemonDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.getData();

        pokemonDB = PokemonDB.getInstanse(this);
        pokemonViewModel.mutableLiveData.observe(this, new Observer<PokemonResponse>() {
            @Override
            public void onChanged(PokemonResponse pokemonResponse) {
                mainBinding.mainTvCount.setText(pokemonResponse.getCount()+"");
                fillRecycleView(pokemonResponse.getResults());
                insertDataInRoom(pokemonResponse.getResults());

            }
        });


        mainBinding.mainTvTitleCount.setOnClickListener(this);
    }

    public void fillRecycleView(ArrayList<Pokemon>pokemons){

        PokemonAdapter adapter =new PokemonAdapter();
        adapter.setList(pokemons);
        mainBinding.mainRv.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.mainRv.setAdapter(adapter);

    }

    public void insertDataInRoom(ArrayList<Pokemon> pokemons){
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                for (Pokemon pokemon:pokemons) {
                    pokemonDB.pokemonDao().setPokemon(pokemon);
                }
            }
        });
        thread.start();
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(this, SecandActivity.class);
        startActivity(intent);
    }
}