package com.example.pokemontestapplication.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemontestapplication.model.Pokemon;
import com.example.pokemontestapplication.model.PokemonResponse;
import com.network.PokemonClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonViewModel extends ViewModel {

    public MutableLiveData<PokemonResponse> mutableLiveData =new MutableLiveData<>();


public void getData(){


    PokemonClient.getInstance().getPokemons().enqueue(new Callback<PokemonResponse>() {
        @Override
        public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {

            String image_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

            ArrayList<Pokemon> pokemons = new ArrayList<>();
            for (Pokemon pokemon:response.body().getResults()) {
                String []url = pokemon.getUrl().split("/");
                String newUrl = image_url+url[url.length-1]+".png";
                pokemon.setUrl(newUrl);
                pokemons.add(pokemon);

            }
            int count = response.body().getCount();
            PokemonResponse pokemonResponse =new PokemonResponse(count,"","",pokemons);

            mutableLiveData.setValue(pokemonResponse);

        }

        @Override
        public void onFailure(Call<PokemonResponse> call, Throwable t) {

        }
    });



}

}
