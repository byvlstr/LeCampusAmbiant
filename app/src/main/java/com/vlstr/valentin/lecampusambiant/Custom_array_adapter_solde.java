package com.vlstr.valentin.lecampusambiant;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ling on 16/12/15.
 */

public class Custom_array_adapter_solde extends ArrayAdapter<ItemSolde> {

    LayoutInflater mInflater ;
    private List<ItemSolde> mItems ;

    public Custom_array_adapter_solde(Context context, List<ItemSolde> items, LayoutInflater inflater) {
        super(context, -1, items);
        this.mInflater = inflater;
        this.mItems = items;
    }

    @ Override
    public View getView ( int position, View convertView, ViewGroup parent )
    {
        // Inflate the custom view
        convertView =   mInflater.inflate(R.layout.custom_list_item_solde, null)  ;

        String titre = mItems.get ( position ).getTitre();
        String soldeTexte = mItems.get ( position ).getSoldeTexte();
        String soldeMontant = mItems.get ( position ).getSoldeMontant();
        String repasRestant = mItems.get ( position ).getRepasRestant();
        String repasTexte = mItems.get ( position ).getRepasTexte();

        SpannableString stitre = new SpannableString( titre);
        SpannableString ssoldeTexte = new SpannableString(soldeTexte);
        SpannableString ssoldeMontant = new SpannableString(soldeMontant);
        SpannableString srepasRestant = new SpannableString(repasRestant);
        SpannableString srepasTexte = new SpannableString(repasTexte);

        stitre.setSpan(new AbsoluteSizeSpan(80), 0, titre.length(), 0);

        //if our balance is under 20 display in red
        if(Float.parseFloat(soldeMontant)<20){
            ssoldeMontant.setSpan(new ForegroundColorSpan(Color.RED),0,soldeMontant.length(),0);
        }else{//display in green
            ssoldeMontant.setSpan(new ForegroundColorSpan(Color.GREEN),0,soldeMontant.length(),0);
        }

        String display = TextUtils.concat(stitre,"\n",ssoldeTexte,ssoldeMontant,"\n",srepasTexte,srepasRestant).toString();

        // Set the Date text
                ((TextView) convertView.findViewById(R.id.textCell)).setText(TextUtils.concat(stitre,"\n",ssoldeTexte,ssoldeMontant,"\n",srepasTexte,srepasRestant))  ;

        return convertView ;
    }




}
