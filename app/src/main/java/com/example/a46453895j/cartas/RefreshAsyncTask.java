package com.example.a46453895j.cartas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.alexvasilkov.events.Event;
import com.alexvasilkov.events.Events;

import java.util.ArrayList;

/**
 * Created by MICHUS on 08/12/2016.
 */



    //Clase que extiende de AsynTask, la cual necesitaremos para trabajar en 2 plano, ya que en primer plano si se demora
    //la ejecución en el tiempo nos fallará la conexión
    class RefreshAsyncTask extends AsyncTask<Void, Void, Void> {
    Context context;

    public RefreshAsyncTask(Context context) {
        this.context=context;
    }

    @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ////antes de mostrar las cartas que aparezca el mensaje
            Events.post("Start-dowloading-data");
        }

        @Override
        //devuelve un array list de objetos Ocarta() para trabajar en 2 plano
        protected Void doInBackground(Void... voids) {
            //creariamos un objeto preference haciendo referencia al metodo de selección a traves de xml string dentro del paquete values y el pref_general dentro del paquetexml
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String srare=preferences.getString("rarity","Common");
            String scolors=preferences.getString("colors","Blue");

            //CartasApi api = new CartasApi();

            //
            ArrayList<Ocarta> cards= CartasApi.getOcartas2(srare,scolors);

            // Log.d("XXXXX", cards.toString());
            //clase con el que crearemos la base de datos

            DataManager.deleteCartas(context);
            DataManager.saveCartas(cards,context);

            return null;
        }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //despues de mostrar las cartas que desaparezca el mensaje
        super.onPostExecute(aVoid);
        Events.post("finish-dowloading-data");
    }

        /*Como el método doInBackground no devuelve datos a la interfaz necesitamos del método OnPostExecute para recoger los datos
    //de este metodo , extraer en este caso el titulo y añadirlo al adapter, que cargara los datons en el layout.
        @Override
        protected void onPostExecute(ArrayList<Ocarta> cards) {
            adapter.clear();
            for (int i = 0; i < cards.size(); ++i) {
                //añadimos en el adapter la informacion de las cartas seleccionadas , los datos precargados en objeto adapter de la clase cardsadapter
                adapter.add(cards.get(i));

            }
       */
    }

