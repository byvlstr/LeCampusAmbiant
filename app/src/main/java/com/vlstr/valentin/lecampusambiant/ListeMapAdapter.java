package com.vlstr.valentin.lecampusambiant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Hugo on 06/01/16.
 */
public class ListeMapAdapter extends ArrayAdapter<String> {
    private final Activity _context;
    private final String[] _text;
    private final boolean[] _favoris;
    private final boolean[] _aime;
    private final int[] _type;

    public ListeMapAdapter(Activity context, String[] text, boolean[] favoris, boolean[] aime, int[] type) {
        super(context, R.layout.element_liste_favoris, text);
        this._context = context;
        this._text = text;
        this._favoris= favoris;
        this._aime = aime;
        this._type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = _context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.element_liste_map, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.text);
        txtTitle.setText(_text[position]);

        if(!_favoris[position]) {
            ImageButton imgFavoris = (ImageButton) rowView.findViewById(R.id.imageStar);
            imgFavoris.setVisibility(View.INVISIBLE);
            imgFavoris.getLayoutParams().width = 0;
        }

        if(!_aime[position]) {
            ImageButton imgAime = (ImageButton) rowView.findViewById(R.id.imageAime);
            imgAime.setVisibility(View.INVISIBLE);
            imgAime.getLayoutParams().width = 0;
        }

        ImageButton imgType = (ImageButton) rowView.findViewById(R.id.imageType);
        if (_type[position] == 1) {
            imgType.setBackgroundResource(R.drawable.ic_dish);
        }
        else if (_type[position] == 2) {
            imgType.setBackgroundResource(R.drawable.ic_burger);
        }
        else if (_type[position] == 3) {
            imgType.setBackgroundResource(R.drawable.ic_pizza);
        }

        return rowView;
    }

}
