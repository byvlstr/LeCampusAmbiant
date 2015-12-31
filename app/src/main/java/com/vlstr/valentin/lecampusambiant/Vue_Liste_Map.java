package com.vlstr.valentin.lecampusambiant;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Vue_Liste_Map extends FragmentActivity implements OnMapReadyCallback{

    private MapFragment mMapFragment;
    ListView listeRestaurants;
    private GoogleMap googleMap = null;
    private ArrayList<Marker> list_marker= null;
    private String[] titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_liste_map);

        listeRestaurants = (ListView) findViewById(R.id.list);


        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        LatLng insa = new LatLng(45.781936, 4.872686);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(insa,15));
        initMapExample();
    }

    private void initMapExample(){
        list_marker = new ArrayList<Marker>();
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.781094, 4.873474))
                .title("Castor&Pollux")));
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.780975, 4.876152))
                .title("Restaurant Universitaire")));
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.784154, 4.874746))
                .title("L'Olivier")));
        titles = new String[list_marker.size()];
        for (int i = 0; i < list_marker.size(); i++){
            titles[i] = list_marker.get(i).getTitle();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,titles);

        listeRestaurants.setAdapter(adapter);
        listeRestaurants.setClickable(true);
    }
}
