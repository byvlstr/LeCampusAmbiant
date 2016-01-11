package com.vlstr.valentin.lecampusambiant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ling on 09/12/15.
 */
public class Vue_Solde extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solde);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        fillList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void fillList(){
        ItemSolde insa  = new ItemSolde();
            insa.setTitre("INSA de Lyon");
            insa.setSoldeTexte("Solde restant: $");
            insa.setSoldeMontant("15.00");
            insa.setRepasRestant("3");
            insa.setRepasTexte("Repas restants: ");

        ItemSolde izly = new ItemSolde();
            izly.setTitre("Izly");
            izly.setSoldeTexte("Solde restant: $");
            izly.setSoldeMontant("30.00");

        ItemSolde moneo = new ItemSolde();
            moneo.setTitre("Moneo");
            moneo.setSoldeTexte("Solde restant: $");
            moneo.setSoldeMontant("0.00");


        ArrayList<ItemSolde> items = new ArrayList<ItemSolde>();
            items.add(insa);
            items.add( izly);
            items.add(moneo);

        Custom_array_adapter_solde adapter = new Custom_array_adapter_solde(this,items,this.getLayoutInflater());
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(adapter);
    }

}
