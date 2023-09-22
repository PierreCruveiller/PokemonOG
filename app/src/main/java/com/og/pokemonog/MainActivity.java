package com.og.pokemonog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.og.pokemonog.databinding.ActivityMainBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private ActivityMainBinding binding;

    BottomNavigationView bottomNavigationView;
    private MyLocationNewOverlay mLocationOverlay;

    @Override
    public void onRequestPermissionsResult(int
                                                   requestCode, @NonNull final String[]
                                                   permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions, grantResults);
        if(grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) {
//on a la permission
        } else {
//afficher un message d’erreur
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ActivityCompat.checkSelfPermission( this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            String[] permissions =
                    {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,
                    permissions,1);
        }

        Context context = this;
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);



        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.pokedex);
        showStartup();
    }

    FragmentManager manager = getSupportFragmentManager();
    MapFragment mapFragment = new MapFragment();
    InventoryFragment inventoryFragment = new InventoryFragment();
    PokedexFragment pokedexFragment = new PokedexFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mapFragment).commit();
                return true;

            case R.id.inventory:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, inventoryFragment).commit();
                return true;

            case R.id.pokedex:
                showStartup();
                return true;
        }
        return false;
    }


    public void showStartup() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonInfoFragment infoFragment = new PokemonInfoFragment();
        PokedexFragment fragment = new PokedexFragment();
        OnClickOnNoteListener listener = new OnClickOnNoteListener(){
            @Override
            public void onClickOnNote(long noteId){
                // How to add a back button in PokemonInfoFragment ?
                /*if (noteId==0) {
                    Log.d("MainActivity","Popping back stack");
                    getFragmentManager().popBackStack();
                }*/
                FragmentTransaction transaction_note = manager.beginTransaction();
                Bundle args = new Bundle();
                args.putLong("index",noteId);
                infoFragment.setArguments(args);
                transaction_note.replace(R.id.fragment_container, infoFragment);
                //transaction.replace(R.id.fragment_container, infoFragment);
                // ajouter addToBackStack pour le retour
                transaction_note.addToBackStack(null);
                transaction_note.commit();
                Log.d("test", String.valueOf(noteId));
                showNoteDetail(noteId);
            }
            private void showNoteDetail(long noteId) {
                Log.d("test","showNoteDetail");
            }
        };
        fragment.setOnClickOnNoteListener(listener);
        infoFragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
        //transaction.replace(R.id.fragment_container, infoFragment);

    }
}