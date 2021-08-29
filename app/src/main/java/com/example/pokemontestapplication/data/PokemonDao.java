package com.example.pokemontestapplication.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemontestapplication.model.Pokemon;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PokemonDao {

    @Insert
     Completable setPokemon(Pokemon pokemon);      //using rxjava
//    public void setPokemon(Pokemon pokemon);

    @Query("SELECT * FROM POKEMON_TABLE")
     Single<List<Pokemon>> getPokemons();               //using rxjava
//    public List<Pokemon> getPokemons();

}
