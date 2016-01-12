package com.vlstr.valentin.lecampusambiant;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

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

public class Vue_Liste_Map extends AppCompatActivity implements OnMapReadyCallback {

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
    //Slider
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    //Swiper

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_liste_map);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        listeRestaurants = (ListView) findViewById(R.id.list);
        Button dragButton = (Button) findViewById(R.id.dragButton);

        //sliding
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        addDrawerItems();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.title_activity_map__fragment,  /* "open drawer" description */
                R.string.drawer_open  /* "close drawer" description */
        ) {
            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(R.string.title_activity_map__fragment);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.drawer_open);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
        dragButton.setOnClickListener(handlerDrag);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private void addDrawerItems() {
        String[] osArray = {"Mes abonnements", "Me connecter", "Paramètres", "Aide"};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                if (item == "Mes abonnements"){
                    Intent intent = new Intent(Vue_Liste_Map.this, Vue_Solde.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        super.onCreateOptionsMenu(menu);
        return true;
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, titles);

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
                Intent intent = new Intent(Vue_Liste_Map.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
