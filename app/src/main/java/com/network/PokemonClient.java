package com.network;

import com.google.gson.Gson;
import com.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonClient {
    public static final String base_url="https://pokeapi.co/api/v2/";
    public PokemonApiService pokemonApiService;
    public static PokemonClient instance;

    public PokemonClient() {

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokemonApiService =retrofit.create(PokemonApiService.class);
    }

    public static PokemonClient getInstance() {
        if(instance == null)
            instance =new PokemonClient();

        return instance;
    }

    public Call<PokemonResponse> getPokemons(){

        return pokemonApiService.getPokemons();
    }
}
