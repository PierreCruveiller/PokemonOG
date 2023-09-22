package com.og.pokemonog;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyThread extends Thread {
    private MyThreadEventListener listener;
    private double south;
    private double west;
    private double north;
    private double east;

    public MyThread(MyThreadEventListener listener, double south, double west, double
            north, double east) {
        this.south = south;
        this.west = west;
        this.north = north;
        this.east = east;
        this.listener = listener;
    }

    public void run() {
        URL url = null;
        String query = "[out:json];node[amenity=pharmacy]("
                + south
                + "," + west + ","
                + north + ","
                + east + "," + ");out;";
        try {
            url = new URL("https://overpass-api.de/api/");
            //URL test:
            //url = new URL("https://overpass-api.de/api/interpreter?data=%5Bout%3Ajson%5D%3Bnode%20%5Bamenity%3Dpharmacy%5D%20%2845.76000839079471%2C4.856901168823242%2C45.790659088204684%2C4.902176856994628%29%3B%20out%3B"); }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection c;
    }
}
