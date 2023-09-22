package com.og.pokemonog;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;


import com.og.pokemonog.databinding.MapFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MapFragment extends Fragment {

    private MyLocationNewOverlay mLocationOverlay;
    private MapFragmentBinding binding;
    private Handler handler = new Handler();
    private Runnable runnable;
    private IMapController mapController;
    private boolean init = true;
    static int delay = 5 * 1000; //Delay for 5 seconds. One second = 1000 milliseconds.
    private int numberPokemonMap = 0;
    private List<Pokemon> pokemonList;
    private List<Pokemon> drawnPokeList;
    private List<Marker> drawnPokeMarkerList;

    public MapFragment() {
        // require a empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.map_fragment, container, false);

        Context context = binding.getRoot().getContext();
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));


        MapView map = binding.mapView;
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(context, map);
        mRotationGestureOverlay.setEnabled(true);
        map.getOverlays().add(mRotationGestureOverlay);

        this.mLocationOverlay = new MyLocationNewOverlay(new
                GpsMyLocationProvider(context), map);
        this.mLocationOverlay.enableMyLocation();
        this.mLocationOverlay.enableFollowLocation();

        binding.mapView.getOverlays().add(mLocationOverlay);
        binding.mapView.setMultiTouchControls(true);

        mapController = binding.mapView.getController();
        mapController.setCenter(new GeoPoint(46.227638,2.213749));
        mapController.setZoom(5.0);




        //@Override
        //public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
/*        Log.d("MapFragment", String.valueOf(this));
        Context context = MainActivity.;
        MapView mMapView = (MapView) container.findViewById(R.id.map_view);
        this.mLocationOverlay = new
                MyLocationNewOverlay(new
                GpsMyLocationProvider(context),mMapView);
        this.mLocationOverlay.enableMyLocation();
        mMapView.getOverlays()
                .add(this.mLocationOverlay);
*/
        /*MapFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false);
        GeoPoint pos = new GeoPoint(45.764043,4.835659);

        Marker marker = new Marker(binding.mapView);
        marker.setPosition(pos);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getResources().getDrawable(R.drawable.p7));
        marker.setTitle("City");
        marker.showInfoWindow();
        marker.setVisible(true);
        Log.d("MapFragment", "marker : "+String.valueOf(marker.isDisplayed()));
        binding.mapView.getOverlays().add(marker);
        binding.mapView.invalidate();*/



        /*MapFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false);
        Log.d("MapFragment", String.valueOf(binding.mapView));
        Marker marker = new Marker(binding.mapView);

        marker.setPosition(pos);
        marker.setAnchor(Marker.ANCHOR_CENTER,
                Marker.ANCHOR_CENTER);
        marker.setIcon(getResources()
                .getDrawable(R.drawable.acier));
        marker.setTitle("Start point");*/


        LocationListener myLocationListener = new
                LocationListener() {
                    @Override
                    public void onLocationChanged(Location newLocation) {
                    }

                    @Override
                    public void onStatusChanged(String provider, int
                            status, Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                };

        return binding.getRoot();
    }
    @Override
    public void onPause() {
        super.onPause();
        this.mLocationOverlay.disableFollowLocation();
        this.mLocationOverlay.disableMyLocation();
        binding.mapView.getOverlays().clear();

        init = true;

        if(handler!=null) {
            handler.removeCallbacks(runnable);
        }
        MapView map = binding.mapView;
        map.getOverlays().clear();
        numberPokemonMap = 0;
    }

    private void setMarkerPosition(Marker marker,double latitude,double longitude) {
        marker.setPosition(new GeoPoint(latitude,longitude));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        marker.setTitle(marker.getTitle());
        binding.mapView.getOverlays().add(marker);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mLocationOverlay.enableMyLocation();
        this.mLocationOverlay.enableFollowLocation();
        binding.mapView.getOverlays().add(mLocationOverlay);

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                createPokemon();
                if (init){
                    defPos();
                    init = false;
                }
                if (numberPokemonMap == 1) {
                    MapView map = binding.mapView;
                    MyThreadEventListener listener = new MyThreadEventListener() {
                        @Override
                        public void onEventInMyThread(JSONObject object) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {//graphic thread
                                    MapView map = binding.mapView;
                                    Context context = binding.getRoot().getContext();
                                    Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
                                    Marker marker = new Marker(map);
                                    try {
                                        marker.setPosition(new GeoPoint(object.getDouble("lat"), object.getDouble("lon")));
                                        marker.setTitle("PokeCenter " + object.getString("name"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                                    //marker.setIcon(ResourcesCompat.getDrawable(context.getResources(),pokemon.getFrontResource(), context.getTheme();
                                    map.getOverlays().add(marker);
                                    map.invalidate();
                                }
                            });
                        }
                    };
                    MyThread thread = new MyThread(listener, map.getBoundingBox().getLatSouth(),
                            map.getBoundingBox().getLonWest(),
                            map.getBoundingBox().getLatNorth(),
                            map.getBoundingBox().getLonEast());
                    thread.start();
                }
                handler.postDelayed(runnable, delay);
            }
        }, delay);
    }
    public void createPokemon() {
        if (numberPokemonMap < 5) {
            MapView map = binding.mapView;
            Pokemon pokemon = getRandomPokemon();
            Context context = binding.getRoot().getContext();
            Configuration.getInstance().load(context,
                    PreferenceManager.getDefaultSharedPreferences(context));
            Marker marker = new Marker(map);
            marker.setPosition(generateRandomGeoPoint());
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setIcon(ResourcesCompat.getDrawable(context.getResources(),
                    pokemon.getFrontResource(), context.getTheme()));
            marker.setTitle(pokemon.getName());
            String description = "Type: " + pokemon.getType1String();
            if (pokemon.getType2() != null){
                description += " " + pokemon.getType2String();
            }
            Log.d("Map",String.valueOf(pokemon.getHeight()));
            description += "<br>Weight: " + pokemon.getWeight();
            description += "<br>Height: " + pokemon.getHeight();
            marker.setSubDescription(description);
            Log.d("MapFragment", String.valueOf(marker.getPosition()));
            map.getOverlays().add(marker);
            map.invalidate();
            numberPokemonMap += 1;
        }
    }

    private void defPos(){
        double myLat = this.mLocationOverlay.getMyLocation().getLatitude();
        double myLon = this.mLocationOverlay.getMyLocation().getLongitude();
        GeoPoint myPos = new GeoPoint(myLat,myLon);

        mapController = binding.mapView.getController();
        mapController.setCenter(myPos);
        mapController.setZoom(17.0);
    }

    private Pokemon getRandomPokemon(){
        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.pkmn));
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();
        String data = "";

        Pokemon pokemon = new
                Pokemon(1,"Bulbizarre",1,POKEMON_TYPE.Grass,POKEMON_TYPE.Poison, "5", "5");

        while(data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            JSONArray array = new JSONArray(builder.toString());
            int pokemon_id = 1 + (int)(Math.random() * 158);
            JSONObject object = array.getJSONObject(pokemon_id );
            String image = object.getString("image");
            int id = getResources().getIdentifier(image,"drawable",
                    binding.getRoot().getContext().getPackageName());
            String type2 = null;
            POKEMON_TYPE type_pokemon_2 = null;
            if (object.has("type2")) {
                type2 = object.getString("type2");
                type_pokemon_2 = POKEMON_TYPE.valueOf(type2);
            }
            pokemon = new Pokemon(pokemon_id,object.getString("name"), id, POKEMON_TYPE.valueOf(object.getString("type1")), type_pokemon_2, object.getString("height"), object.getString("weight"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemon;
    }
    /*

    private GeoPoint getRandomGeoPoint() {
        double radius = 50;

        double x0 = this.mLocationOverlay.getMyLocation().getLatitude();
        double y0 = this.mLocationOverlay.getMyLocation().getLongitude();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;

        double u = Math.random();
        double v = Math.random();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(y0);

        double foundLatitude = new_x + x0;
        double foundLongitude = y + y0;

        return new GeoPoint(foundLatitude,foundLongitude);
    }*/

    private GeoPoint generateRandomGeoPoint(){
        double myLat = this.mLocationOverlay.getMyLocation().getLatitude();
        double myLon = this.mLocationOverlay.getMyLocation().getLongitude();

        double radToDeg = 50 / 111000f;

        double rand_1 = Math.random();
        double rand_2 = Math.random();

        double genLat = ((radToDeg * Math.sqrt(rand_1) * Math.cos(2 * Math.PI * rand_2)) / Math.cos(myLon)) + myLat;
        double genLon = radToDeg * Math.sqrt(rand_1) * Math.sin(2 * Math.PI * rand_2) + myLon;

        return new GeoPoint(genLat,genLon);
    }
}


