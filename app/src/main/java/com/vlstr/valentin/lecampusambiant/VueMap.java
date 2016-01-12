package com.vlstr.valentin.lecampusambiant;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Aiebobo on 12/01/2016.
 */
public class VueMap extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    ListView listeRestaurants;
    private MapFragment mMapFragment;
    View.OnClickListener handlerDrag = new View.OnClickListener() {
        public void onClick(View v) {
            if (mMapFragment.getView().getVisibility() == View.VISIBLE) {
                mMapFragment.getView().setVisibility(View.INVISIBLE);
                LinearLayout.LayoutParams paramsMap = (LinearLayout.LayoutParams) mMapFragment.getView().getLayoutParams();
                LinearLayout.LayoutParams paramsList = (LinearLayout.LayoutParams) listeRestaurants.getLayoutParams();
                paramsList.weight = 95;
                paramsMap.weight = 0;
                mMapFragment.getView().setLayoutParams(paramsMap);
                listeRestaurants.setLayoutParams(paramsList);
            } else {
                mMapFragment.getView().setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams paramsMap = (LinearLayout.LayoutParams) mMapFragment.getView().getLayoutParams();
                LinearLayout.LayoutParams paramsList = (LinearLayout.LayoutParams) listeRestaurants.getLayoutParams();
                paramsList.weight = 45;
                paramsMap.weight = 40;
                mMapFragment.getView().setLayoutParams(paramsMap);
            }
        }
    };

    ArrayList<Marker> list_marker = new ArrayList<Marker>();
    private boolean isSpeakButtonLongPressed = false;

    private GoogleMap googleMap = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        listeRestaurants = (ListView) rootView.findViewById(R.id.list);
        Button dragButton = (Button) rootView.findViewById(R.id.dragButton);
        mMapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
        dragButton.setOnClickListener(handlerDrag);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        LatLng insa = new LatLng(45.781936, 4.872686);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(insa, 15));
        initMapExample();
    }

    private void initMapExample() {

        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.781094, 4.873474))
                .title("Castor&Pollux")));
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.780975, 4.876152))
                .title("Restaurant Universitaire")));
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.784154, 4.874746))
                .title("L'Olivier")));
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.777173, 4.874529))
                .title("Tacos Snoop Dogg")));
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.779932, 4.877215))
                .title("Toï Toï Le Zinc")));
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.778924, 4.872799))
                .title("Ninkasi La Doua")));
        list_marker.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.778672, 4.873654))
                .title("Mosaïc")));
        String[] titles = new String[list_marker.size()];
        for (int i = 0; i < list_marker.size(); i++) {
            titles[i] = list_marker.get(i).getTitle();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, titles);

        listeRestaurants.setAdapter(adapter);
        listeRestaurants.setClickable(true);
        listeRestaurants.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {
                String item = (String) arg0.getItemAtPosition(position);
                for (int i = 0; i < list_marker.size(); i++){
                    Marker selected = list_marker.get(i);
                    if (selected.getTitle().equals(item)) {
                        mMapFragment.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(selected.getPosition(),17), 1000, null);
                        selected.showInfoWindow();
                        isSpeakButtonLongPressed = true;
                        break;
                    }
                }

                return true;
            }
        });
        listeRestaurants.setOnTouchListener(new AdapterView.OnTouchListener() {
            @Override
            public boolean onTouch(View pView, MotionEvent pEvent) {
                pView.onTouchEvent(pEvent);
                // We're only interested in when the button is released.
                if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isSpeakButtonLongPressed) {
                        // Do something when the button is released.
                        mMapFragment.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.781936, 4.872686),15), 1000, null);
                        isSpeakButtonLongPressed = false;
                    }
                }
                return false;
            }
        });
        listeRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0,
                                    View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
