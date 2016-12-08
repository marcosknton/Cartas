package com.example.a46453895j.cartas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.widget.CursorAdapter;
import android.app.ProgressDialog;


import com.alexvasilkov.events.Events;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartasFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

   // private ArrayList<Ocarta> items;
    //private CardsAdapter adapter;
    private CartasCursorAdapter adapter;
    private ProgressDialog dialog;
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

/*
        String[] data = {
                "carta 1",
                "carta 2",
                "carta 3",
                "carta 4",

        };
        //con el metodo asList podemos introducir dentro de un array dinamica mas de una string a traves de un array de strings
        items = new ArrayList<>(Arrays.asList(data));
*/
        //items = new ArrayList<>();
        //el adaptador estará formado por getcontext,por el layout que repetiremos por item, y por los datos de cada item
        //adapter = new CardsAdapter(getContext(), R.layout.titulo_cartas, items);
        adapter=new CartasCursorAdapter(getContext(),Ocarta.class);

        //cargamos un mensaje de cargando
        dialog =new ProgressDialog((getContext()));
        dialog.setMessage("Actualizando...");

        //al list view lo inflamos con el adapter
        lvCartas.setAdapter(adapter);

        lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        /*con el onitemclickl  podemos realizar cualquier acción al pulsarse sobre un elemento de la lista
                        Referencia al control lista que ha recibido el click (AdapterView<?> adapterView).
                        Referencia al objeto View correspondiente al ítem pulsado de la lista (View view).
                        Posición del elemento pulsado dentro del adaptador de la lista (int i).
                        Id del elemento pulsado (long l).

                        */
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Ocarta carta=(Ocarta) adapterView.getItemAtPosition(i);
                            //intent que cargará la activity
                            if(!esTablet()){
                                Intent intent=new Intent(getContext(),DetailActivity.class);
                                intent.putExtra("carta",carta);
                                startActivity(intent);
                            }
                            else {
                                Events.create("carta-seleccionada").param(carta).post();
                            }

                        }
                    });
    getLoaderManager().initLoader(0,null, this);
        return view;
    }

    @Events.Subscribe("Start-dowloading-data")
    void preRefresh(){
        dialog.show();
    }
    @Events.Subscribe("finish-dowloading-data")
    void afterRefresh(){
        dialog.dismiss();
    }

    private boolean esTablet() {
        return getResources().getBoolean(R.bool.tablet);
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
    //metodo que actualiza los datos solo abrir la activity
    @Override
        public void onStart() {
            super.onStart();
            refresh();
        Events.register(this);
        }
    public void refresh() {
        RefreshAsyncTask refreshAsyncTask = new RefreshAsyncTask(getActivity().getApplicationContext());
        refreshAsyncTask.execute();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }




    /*Clase que extiende de AsynTask, la cual necesitaremos para trabajar en 2 plano, ya que en primer plano si se demora
    //la ejecución en el tiempo nos fallará la conexión
    class RefreshAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ////antes de mostrar las cartas que aparezca el mensaje
            dialog.show();
        }

        @Override
        //devuelve un array list de objetos Ocarta() para trabajar en 2 plano
        protected Void doInBackground(Void... voids) {
            //creariamos un objeto preference haciendo referencia al metodo de selección a traves de xml string dentro del paquete values y el pref_general dentro del paquetexml
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String srare=preferences.getString("rarity","Common");
            String scolors=preferences.getString("colors","Blue");

            //CartasApi api = new CartasApi();

            //
            ArrayList<Ocarta> cards= CartasApi.getOcartas2(srare,scolors);

           // Log.d("XXXXX", cards.toString());
           //clase con el que crearemos la base de datos

            DataManager.deleteCartas(getContext());
            DataManager.saveCartas(cards,getContext());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //despues de mostrar las cartas que desaparezca el mensaje
            dialog.dismiss();
        }

        Como el método doInBackground no devuelve datos a la interfaz necesitamos del método OnPostExecute para recoger los datos
    //de este metodo , extraer en este caso el titulo y añadirlo al adapter, que cargara los datons en el layout.
        @Override
        protected void onPostExecute(ArrayList<Ocarta> cards) {
            adapter.clear();
            for (int i = 0; i < cards.size(); ++i) {
                //añadimos en el adapter la informacion de las cartas seleccionadas , los datos precargados en objeto adapter de la clase cardsadapter
                adapter.add(cards.get(i));

            }
       }
       */


    }


