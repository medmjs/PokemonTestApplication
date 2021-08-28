package com.example.pokemontestapplication.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokemontestapplication.model.Pokemon;

@Database(entities = Pokemon.class,version = 2)
public abstract class PokemonDB extends RoomDatabase {
    public static PokemonDB instanse;
    public abstract PokemonDao pokemonDao();

    public static synchronized PokemonDB getInstanse(Context context) {
        if(instanse == null){
            instanse = Room.databaseBuilder(context.getApplicationContext(),PokemonDB.class,"pokemon_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanse;
    }
}
