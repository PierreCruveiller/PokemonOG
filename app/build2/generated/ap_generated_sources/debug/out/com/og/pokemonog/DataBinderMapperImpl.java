package com.og.pokemonog;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.og.pokemonog.databinding.ActivityMainBindingImpl;
import com.og.pokemonog.databinding.PokedexFragmentBindingImpl;
import com.og.pokemonog.databinding.PokemonInfoFragmentBindingImpl;
import com.og.pokemonog.databinding.PokemonInfoItemBindingImpl;
import com.og.pokemonog.databinding.PokemonItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_POKEDEXFRAGMENT = 2;

  private static final int LAYOUT_POKEMONINFOFRAGMENT = 3;

  private static final int LAYOUT_POKEMONINFOITEM = 4;

  private static final int LAYOUT_POKEMONITEM = 5;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(5);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.og.pokemonog.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.og.pokemonog.R.layout.pokedex_fragment, LAYOUT_POKEDEXFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.og.pokemonog.R.layout.pokemon_info_fragment, LAYOUT_POKEMONINFOFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.og.pokemonog.R.layout.pokemon_info_item, LAYOUT_POKEMONINFOITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.og.pokemonog.R.layout.pokemon_item, LAYOUT_POKEMONITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_POKEDEXFRAGMENT: {
          if ("layout/pokedex_fragment_0".equals(tag)) {
            return new PokedexFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for pokedex_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_POKEMONINFOFRAGMENT: {
          if ("layout/pokemon_info_fragment_0".equals(tag)) {
            return new PokemonInfoFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for pokemon_info_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_POKEMONINFOITEM: {
          if ("layout/pokemon_info_item_0".equals(tag)) {
            return new PokemonInfoItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for pokemon_info_item is invalid. Received: " + tag);
        }
        case  LAYOUT_POKEMONITEM: {
          if ("layout/pokemon_item_0".equals(tag)) {
            return new PokemonItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for pokemon_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(10);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "desc");
      sKeys.put(2, "front");
      sKeys.put(3, "height");
      sKeys.put(4, "name");
      sKeys.put(5, "number");
      sKeys.put(6, "pokemonViewModel");
      sKeys.put(7, "type1");
      sKeys.put(8, "type2");
      sKeys.put(9, "weight");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(5);

    static {
      sKeys.put("layout/activity_main_0", com.og.pokemonog.R.layout.activity_main);
      sKeys.put("layout/pokedex_fragment_0", com.og.pokemonog.R.layout.pokedex_fragment);
      sKeys.put("layout/pokemon_info_fragment_0", com.og.pokemonog.R.layout.pokemon_info_fragment);
      sKeys.put("layout/pokemon_info_item_0", com.og.pokemonog.R.layout.pokemon_info_item);
      sKeys.put("layout/pokemon_item_0", com.og.pokemonog.R.layout.pokemon_item);
    }
  }
}
