package com.example.a46453895j.cartas;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import nl.qbusict.cupboard.Cupboard;

/**
 * Created by 46453895j on 25/11/16.
 */

public class CartasCursorAdapter extends CupboardCursorAdapter<Ocarta>{

    public CartasCursorAdapter(Context context, Class<Ocarta> entidad) {
        super(context, entidad);
    }

    @Override
    public View newView(Context context, Ocarta model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.titulo_cartas, parent, false);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Ocarta carta) {
        ;
        TextView color=(TextView) view.findViewById(R.id.Tvcolor);
        TextView titulo=(TextView) view.findViewById(R.id.TvTitulos);
        TextView tipo=(TextView) view.findViewById(R.id.TvTipo);
        ImageView imagen=(ImageView) view.findViewById(R.id.Icarta);


        titulo.setText("name: "+carta.getTitulo());
        tipo.setText("type: "+carta.getTypes());
        color.setText("color: "+carta.getColors());
        Glide.with(context).load(carta.getImageUrl()).into(imagen);
    }

}
