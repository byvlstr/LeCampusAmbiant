package com.vlstr.valentin.lecampusambiant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Hugo on 06/01/16.
 */
public class VueFavoris extends Fragment {

    ListView list;
    String[] text = { "Castor&Pollux","Restaurant Universitaire", "L'Olivier"};

    public VueFavoris() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        list = (ListView) rootView.findViewById(R.id.ListView);
        ListeFavorisAdapter adapter = new ListeFavorisAdapter(getActivity() , text );
        list.setAdapter(adapter);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0,
                                    View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

}