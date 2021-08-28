package com.example.pokemontestapplication.ui;

;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.pokemontestapplication.R;
import com.example.pokemontestapplication.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    List<Pokemon> pokemonModels = new ArrayList<>();


    public void setList(List<Pokemon> moviesList) {
        this.pokemonModels = moviesList;
        notifyDataSetChanged();
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pokemon_card, parent, false);
        PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(v);
        return pokemonViewHolder;
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonModels.get(position);
        holder.textView.setText(pokemon.getName());
//        holder.imageView.setImageURI(Uri.parse(pokemon.getUrl()));

        Picasso.get()
                .load(pokemon.getUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return pokemonModels.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.row_iv);
            textView =itemView.findViewById(R.id.row_tv_name);

        }
    }
}
