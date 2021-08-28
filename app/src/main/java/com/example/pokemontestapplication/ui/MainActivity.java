package com.example.pokemontestapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.pokemontestapplication.ui.PokemonViewModel;
import com.example.pokemontestapplication.ui.PokemonAdapter;
import com.example.pokemontestapplication.databinding.ActivityMainBinding;
import com.example.pokemontestapplication.model.Pokemon;
import com.example.pokemontestapplication.model.PokemonResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding mainBinding;
    PokemonViewModel pokemonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.getData();

        pokemonViewModel.mutableLiveData.observe(this, new Observer<PokemonResponse>() {
            @Override
            public void onChanged(PokemonResponse pokemonResponse) {
                mainBinding.mainTvCount.setText(pokemonResponse.getCount()+"");
                fillRecycleView(pokemonResponse.getResults());
            }
        });




    }

    public void fillRecycleView(ArrayList<Pokemon>pokemons){

        PokemonAdapter adapter =new PokemonAdapter();
        adapter.setList(pokemons);
        mainBinding.mainRv.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.mainRv.setAdapter(adapter);

    }
}