package com.og.pokemonog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.og.pokemonog.databinding.PokemonInfoItemBinding;
import com.og.pokemonog.databinding.PokemonItemBinding;

import java.util.List;

public class PokemonInfoAdapter extends RecyclerView.Adapter<PokemonInfoAdapter.ViewHolder> {
    Pokemon pokemon;
    public PokemonInfoAdapter(Pokemon pokemon) {
        assert pokemon != null;
        this.pokemon =pokemon;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonInfoItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.pokemon_info_item, parent, false);
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Pokemon pokemon = pokemonList.get(position);
        Log.d("PIA","pokemon");
        Log.d("PIA", String.valueOf(pokemon.getDesc()));
        holder.viewModel.setPokemon(pokemon);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public OnClickOnNoteListener listener;
    public void setOnClickListener(OnClickOnNoteListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private PokemonInfoItemBinding binding;
        private PokemonViewModel viewModel = new PokemonViewModel();
        ViewHolder(PokemonInfoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            Log.d("PIA","-------/------");
            Log.d("PIA", String.valueOf(viewModel));
            this.binding.setPokemonViewModel(viewModel);

            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("test","page pokemon");
                    listener.onClickOnNote(0);
                }
            });
        }
    }
}