package com.example.a46453895j.cartas;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by michus on 20/10/2016.
 */

public class CartasApi {

    private  String url= "https://api.magicthegathering.io/v1/cards?pageSize=100";

    /**
     *
     * @return devuelve un array con todos los objetos Ocarta
     */
    public ArrayList<Ocarta> getOcartas() {

        ArrayList<Ocarta> lista = new ArrayList<>();
        try {
            String JsonResponse = HttpUtils.get(url);
            //creamos un objeto json y como contenido le introducimos el string resultado de la pagina
            JSONObject json = new JSONObject(JsonResponse);
            //creamos un array de cada elemento que forma parte de la estrucutra del objeto json ,guiandonos por el elemento que
            //envueleve toda la estructura que es cards
            JSONArray jsoncartas = json.getJSONArray("cards");
            String titulo;
            //recorremos el array de elementos, extraemos la info que nos interesa y la introducimos como atributo
            //dentro el objeto Ocarta, que posteriormente guardamos uno por uno en el array de objetos Ocarta
            for (int i = 0; i < jsoncartas.length(); ++i) {
                JSONObject object = jsoncartas.getJSONObject(i);
                titulo = object.getString("name");
                Ocarta carta=new Ocarta(titulo);
                lista.add(carta);
            }

        }

        catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
