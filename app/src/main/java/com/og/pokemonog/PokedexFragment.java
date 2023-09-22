package com.og.pokemonog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.og.pokemonog.R;
import com.og.pokemonog.databinding.PokedexFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokedexFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        List<Pokemon> pokemonList = new ArrayList<>();

        //{"name":"Bulbizarre", "image":"p1", "type1":"plante", "type2":"poison"},
        //  {"name":"Herbizarre", "image":"p2", "type1":"plante", "type2":"poison"},
        //  {"name":"Florizarre", "image":"p3", "type1":"plante", "type2":"poison"},
        //  {"name":"Salamèche", "image":"p4", "type1":"feu"}

        //Ouverture du fichier res/raw
        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.pkmn));
        // Ouverture du fichier dans assets
        // InputStreamReader isr =
        // new InputStreamReader(getResources().getAssets().open("poke.json"));
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();
        String data = "";
        //lecture du fichier. data == null => EOF
        while(data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Traitement du fichier
        try {
            JSONArray array = new JSONArray(builder.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String image = object.getString("image");
                String weight = object.getString("weight");
                String height = object.getString("height");
                String type1 = object.getString("type1");
                String type2 = null;
                if (object.has("type2"))
                    type2 = object.getString("type2");
                int id = getResources().getIdentifier(image,"drawable", getContext().getPackageName());
                pokemonList.add(new Pokemon(i+1,name,id, POKEMON_TYPE.valueOf(type1), type2!=null ? POKEMON_TYPE.valueOf(type2) : null,height,weight));
                Log.d("PokedexFragment", String.valueOf(i+1));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        PokemonListAdapter adapter = new PokemonListAdapter(pokemonList);
        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokedex_fragment, container, false);
        binding.pokemonList.setAdapter(adapter);
        adapter.setOnClickListener(listener);
        binding.pokemonList.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));
        return binding.getRoot();
    }

    private OnClickOnNoteListener listener;
    public void setOnClickOnNoteListener(OnClickOnNoteListener listener) {
        this.listener = listener;
    }

    public void onEventFunction(long noteId) {
        if (listener != null)
            listener.onClickOnNote(noteId);
    }
}