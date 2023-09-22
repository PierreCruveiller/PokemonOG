package com.og.pokemonog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.og.pokemonog.R;

import java.io.*;

public class InventoryFragment extends Fragment {

    public InventoryFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.inventory_fragment, container, false);
    }
}
