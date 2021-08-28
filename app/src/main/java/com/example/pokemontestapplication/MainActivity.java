package com.example.pokemontestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.PokemonAdapter;
import com.example.pokemontestapplication.databinding.ActivityMainBinding;
import com.model.Pokemon;
import com.model.PokemonResponse;
import com.network.PokemonClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());


        PokemonClient pokemonClient =new PokemonClient();
        pokemonClient.getPokemons().enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                mainBinding.mainTvTitleCount.setText("Count : ");
                mainBinding.mainTvCount.setText(response.body().getCount()+"");

                String image_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";


                ArrayList<Pokemon>pokemons = new ArrayList<>();
                for (Pokemon pokemon:response.body().getResults()) {
                    String []url = pokemon.getUrl().split("/");
                    String newUrl = image_url+url[url.length-1]+".png";
                    pokemon.setUrl(newUrl);
                    pokemons.add(pokemon);
                }
                Log.d(TAG,"MEDMJS 22count array is => "+pokemons.size());
                Log.d(TAG,"MEDMJS 22count array is => "+pokemons.get(1).getName());
                Log.d(TAG,"MEDMJS 22count array is => "+pokemons.get(1).getUrl());
                Log.d(TAG,"MEDMJS22 count array is => "+ Uri.parse(pokemons.get(1).getUrl()));
                fillRecycleView(pokemons);



            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

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