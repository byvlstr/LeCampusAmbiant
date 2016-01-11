package com.vlstr.valentin.lecampusambiant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Hugo on 06/01/16.
 */
public class ListeFavorisAdapter extends ArrayAdapter<String> {
    private final Activity _context;
    private final String[] _text;

    public ListeFavorisAdapter(Activity context, String[] text) {
        super(context, R.layout.element_liste_favoris, text);
        this._context = context;
        this._text = text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = _context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.element_liste_favoris, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.text);
        txtTitle.setText(_text[position]);

        return rowView;
    }

}
