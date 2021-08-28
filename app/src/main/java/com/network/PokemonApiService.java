package com.network;

import com.example.pokemontestapplication.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonApiService {
    @GET("pokemon")
    public Call<PokemonResponse> getPokemons();
}
