package com.example.a46453895j.cartas;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import android.support.v4.content.CursorLoader;
import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 46453895j on 22/11/16.
 */

public class DataManager {

    private static UriHelper helper= UriHelper.with(Contentprovider.AUTHORITY);
    //obtiene una direccion uri del objeto carta
    private static Uri cartasUri=helper.getUri(Ocarta.class);
    //cargamos en la base de datos la direccion del objeto carta,la clase cata, y la seleccion del setting
    static void saveCartas(ArrayList<Ocarta> cards,Context context) {
       cupboard().withContext(context).put(cartasUri, Ocarta.class, cards);
   }
    //borramos las cartas antes de insertar las nuevas
    static void deleteCartas(Context context) {
        cupboard().withContext(context).delete(cartasUri,"_id > ?","0");
    }

    //metodo que devuelve el cursor que cargara en el adapter cada linea de la listview
    static CursorLoader getCursorLoader (Context context){
        return new CursorLoader(context,cartasUri,null,null,null,null);
    }
}
