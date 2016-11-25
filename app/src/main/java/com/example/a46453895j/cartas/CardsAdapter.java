package com.example.a46453895j.cartas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MICHUS on 01/11/2016.
 */

public class CardsAdapter extends ArrayAdapter <Ocarta> {
    //context punto de partida al que se ceñirá para su ciclo de vida
    //List objeto del que obtendremos la información
    public CardsAdapter(Context context, int resource, List<Ocarta> objects){
        super(context,resource,objects);
    }

    //reciclaje de la lista cadavez que deslicemos con el dedo la pantalla
    @Override

    //view convertview la propia vista
    //viewgroup parent la vista padre
    public View getView(int position, View convertView, ViewGroup parent) {
        Ocarta carta=getItem(position);

        //si convertview es null (no existe )no reciclamos si no que la inflamos
        //una vez creada el convertview se reciclara automáticamente
        if (convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.titulo_cartas, parent, false);
        }

        TextView color=(TextView) convertView.findViewById(R.id.Tvcolor);
        TextView titulo=(TextView) convertView.findViewById(R.id.TvTitulos);
        TextView tipo=(TextView) convertView.findViewById(R.id.TvTipo);
        ImageView imagen=(ImageView) convertView.findViewById(R.id.Icarta);


        titulo.setText("name: "+carta.getTitulo());
        tipo.setText("type: "+carta.getTypes());
        color.setText("color: "+carta.getColors());
        Glide.with(getContext()).load(carta.getImageUrl()).into(imagen);

        return convertView;
    }
}
