package com.example.a46453895j.cartas;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import static android.R.attr.id;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartasFragment extends Fragment {

    private ArrayList<String> items ;
    private ArrayAdapter<String> adapter;

    public CartasFragment() {
    }
    //agregamos el menu en el fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
                "El padrino Parte II",
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
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartas,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.MiRefresh){
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh(){
        Snackbar.make(getView(),"esto funsiona",Snackbar.LENGTH_LONG).show();
    }
}
