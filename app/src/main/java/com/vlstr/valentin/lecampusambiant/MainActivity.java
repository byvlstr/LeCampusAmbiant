package com.vlstr.valentin.lecampusambiant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean favSet = false;
    boolean changeStar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ((TextView)findViewById(R.id.textView5)).setOnClickListener(handlerPlat);
        ((TextView)findViewById(R.id.textView7)).setOnClickListener(handlerPlat);
        ((TextView)findViewById(R.id.textView9)).setOnClickListener(handlerPlat);
        ((TextView)findViewById(R.id.textView10)).setOnClickListener(handlerPlat);
        ((TextView)findViewById(R.id.textView11)).setOnClickListener(handlerPlat);
    }
    View.OnClickListener handlerPlat = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Vue_Plat.class);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.star) {
            if (changeStar == false) {
                item.setIcon(R.drawable.ic_star_border_black_24dp);
                changeStar = true;
            }else {
                item.setIcon(R.drawable.ic_star_black_24dp);
                changeStar = false;
            }
        }

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    @SuppressLint("ValidFragment")
    public static class PlaceholderFragment extends Fragment {

        String value;

        public PlaceholderFragment(String aValue) {
            value = aValue;
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            if (value.equals("vueResto")) {
                rootView = inflater.inflate(R.layout.fragment_vue__resto, container, false);
            } else if (value.equals("")) {
            }


            return rootView;
        }
    }
}


