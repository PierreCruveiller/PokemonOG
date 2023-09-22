package com.og.pokemonog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.og.pokemonog.databinding.PokemonInfoFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokemonInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int index = (int) args.getLong("index", 0);
        Log.d("PokemonInfoFragment", String.valueOf(index));
        List<Pokemon> pokemonList = new ArrayList<>();

        //  {"name":"Bulbizarre", "image":"p1", "type1":"plante", "type2":"poison"},
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
        while (data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Traitement du fichier
        Pokemon pokemon = null;
        try {
            JSONArray array = new JSONArray(builder.toString());
            Log.d("PokemonInfoFragment", String.valueOf(array.getJSONObject(index - 1)));
            JSONObject object = array.getJSONObject(index - 1);
            String name = object.getString("name");
            String image = "p" + String.valueOf(index);
            String weight = object.getString("weight");
            String height = object.getString("height");
            int id = getResources().getIdentifier(image, "drawable", getContext().getPackageName());
            String type1 = object.getString("type1");
            String type2 = null;
            if (object.has("type2"))
                type2 = object.getString("type2");

            pokemon = new Pokemon(index, name, id, POKEMON_TYPE.valueOf(type1), type2 != null ? POKEMON_TYPE.valueOf(type2) : null, height, weight);
            pokemon.setDesc(object.getString("desc"));
            Log.d("PokemonInfoFragment", name);
            pokemon.setHeight(object.getString("height"));
            pokemon.setWeight(object.getString("weight"));
            Log.d("PokemonInfoFragment", (object.getString("height")));
            Log.d("PokemonInfoFragment", "ok2");
            /*for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String image = object.getString("image");
                String type1 = object.getString("type1");
                String type2 = null;
                if (object.has("type2"))
                    type2 = object.getString("type2");
                int id = getResources().getIdentifier(image,"drawable", getContext().getPackageName());
                pokemonList.add(new Pokemon(i+1,name,id, POKEMON_TYPE.valueOf(type1), type2!=null ? POKEMON_TYPE.valueOf(type2) : null));
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("PokemonInfoFragment", "---------------------");
        PokemonInfoAdapter adapter = new PokemonInfoAdapter(pokemon);
        Log.d("PokemonInfoFragment", String.valueOf(adapter.pokemon.getName()));
        PokemonInfoFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokemon_info_fragment, container, false);
        binding.pokemonInfo.setAdapter(adapter);
        Log.d("PokemonInfoFragment", String.valueOf(binding.pokemonInfo.getId()));
        adapter.setOnClickListener(listener);
        binding.pokemonInfo.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));

        return binding.getRoot();
    }
    private OnClickOnNoteListener listener;
    public void setOnClickOnNoteListener(OnClickOnNoteListener listener) {
        this.listener = listener;
    }
}
