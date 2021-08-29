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
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

         getDataFromRoom();






    }

    public void getDataFromRoom() {
        pokemonDB.pokemonDao().getPokemons()
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<List<Pokemon>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess( List<Pokemon> pokemons) {
                fillRecycleView((ArrayList<Pokemon>) pokemons);

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }


    public void fillRecycleView(ArrayList<Pokemon> pokemons) {
        PokemonAdapter adapter = new PokemonAdapter();
        adapter.setList(pokemons);
        secandBinding.secandRv.setLayoutManager(new LinearLayoutManager(this));
        secandBinding.secandRv.setAdapter(adapter);
    }


}