package com.example.pokemontestapplication.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemontestapplication.model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    public void setPokemon(Pokemon pokemon);

    @Query("SELECT * FROM POKEMON_TABLE")
    public List<Pokemon> getPokemons();

}
