package com.example.a46453895j.cartas;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartasFragment extends Fragment {

    private ArrayList<String> items;
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
        View view = inflater.inflate(R.layout.fragment_cartas, container, false);
        ListView lvCartas = (ListView) view.findViewById(R.id.LvCartas);


        String[] data = {
                "carta 1",
                "carta 2",
                "carta 3",
                "carta 4",

        };
        //con el metodo asList podemos introducir dentro de un array dinamica mas de una string a traves de un array de strings
        items = new ArrayList<>(Arrays.asList(data));

        //el adaptador estará formado por getcontext,por el layout que repetiremos por item, por el texview que con tiene los datos y por los datos de cada item
        adapter = new ArrayAdapter<>(getContext(), R.layout.titulo_cartas, R.id.TvTitulos, items);
        lvCartas.setAdapter(adapter);


        return view;
    }

    //inflamos el menu_cartas
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartas, menu);
    }
    //habilitamos el menuitem con las funciones que vaya a realizar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.MiRefresh) {
            Snackbar.make(getView(), "actualizando...", Snackbar.LENGTH_LONG).show();
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refresh() {
        RefreshAsyncTask refreshAsyncTask = new RefreshAsyncTask();
        refreshAsyncTask.execute();
    }

    //Clase que extiende de AsynTask, la cual necesitaremos para trabajar en 2 plano, ya que en primer plano si se demora
    //la ejecución en el tiempo nos fallará la conexión
    class RefreshAsyncTask extends AsyncTask<Void, Void, ArrayList<Ocarta>> {
        @Override
        //devuelve un array list de objetos Ocarta() para trabajar en 2 plano
        protected ArrayList<Ocarta> doInBackground(Void... voids) {
            CartasApi api = new CartasApi();
            ArrayList<Ocarta> cards = api.getOcartas();
            /*
            for (int i = 0; i < cards.size(); ++i) {
                Log.d("DEBUG", cards.get(i).toString());
            }
            */
            return cards;
        }
    //Como el método doInBackground no devuelve datos a la interfaz necesitamos del método OnPostExecute para recoger los datos
    //de este metodo , extraer en este caso el titulo y añadirlo al adapter, que cargara los datons en el layout.
        @Override
        protected void onPostExecute(ArrayList<Ocarta> cards) {
            adapter.clear();
            for (int i = 0; i < cards.size(); ++i) {
                adapter.add(cards.get(i).getTitulo());
            }
        }
    }

}