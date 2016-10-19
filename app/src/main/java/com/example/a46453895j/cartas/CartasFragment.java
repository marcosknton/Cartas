package com.example.a46453895j.cartas;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartasFragment extends Fragment {

    private ArrayList<String> items = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    public CartasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cartas, container, false);
        ListView lvCartas = (ListView) view.findViewById(R.id.LvCartas);

        String[] data = {
                "Los 400 golpes",
                "El odio",
                "El padrino",
                "El padrino. Parte II",
                "Ocurrió cerca de su casa",
                "Infiltrados",
                "Umberto D."
        };

        //con el metodo asList podemos introducir dentro de un array dinamica mas de una string a traves de un array de strings
        items = new ArrayList<>(Arrays.asList(data));

        //el adaptador estará formado por getcontext,por el layout que repetiremos por item, por el texview que con tiene los datos y por los datos de cada item
        adapter = new ArrayAdapter<>(getContext(), R.layout.titulo_cartas, R.id.TvTitulos, items);
        lvCartas.setAdapter(adapter);

        return view;
    }
}
