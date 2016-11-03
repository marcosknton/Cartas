package com.example.a46453895j.cartas;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by michus on 20/10/2016.
 */

public class CartasApi {

    public CartasApi() {

    }

    private String url = "https://api.magicthegathering.io/v1/cards?";

    /**
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
            //recorremos el array de elementos, extraemos la info que nos interesa y la introducimos como atributo
            //dentro el objeto Ocarta, que posteriormente guardamos uno por uno en el array de objetos Ocarta
            for (int i = 0; i < jsoncartas.length(); i++) {
                JSONObject object = jsoncartas.getJSONObject(i);
                String titulo = object.getString("name");
                String rarity = object.getString("rarity");
                //con optjsonarray si el valor del objetos que buscamos es nulo , no salta la excepcion por los contrario
                //podemos extraer el string de ese objetos en la posicion 0 ya que es un array de objetos json
                JSONArray jsoncolors = object.optJSONArray("colors");
                String colors="";
                if(jsoncolors != null) {
                    colors=jsoncolors.get(0).toString();
                }
                String imageUrl = object.getString("imageUrl");
                JSONArray jsontypes=object.getJSONArray("types");
                String types = jsontypes.get(0).toString();
                Ocarta carta = new Ocarta(titulo, types, imageUrl, rarity,colors);
                lista.add(carta);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <lista.size() ; i++) {
            Log.d("DEBUG", lista.get(i).getTitulo());
        }
        return lista;
    }
/*
    public ArrayList<Ocarta> GetSeleccion(String rareza) {

        ArrayList<Ocarta> seleccion = new ArrayList<>();
        try {
            String JsonResponse = HttpUtils.get(url);
            //creamos un objeto json y como contenido le introducimos el string resultado de la pagina
            JSONObject json = new JSONObject(JsonResponse);
            //creamos un array de cada elemento que forma parte de la estrucutra del objeto json ,guiandonos por el elemento que
            //envueleve toda la estructura que es cards
            JSONArray jsoncartas = json.getJSONArray("cards");
            String titulo, rarity,colors, imageUrl, types;
            //recorremos el array de elementos, extraemos la info que nos interesa y la introducimos como atributo
            //dentro el objeto Ocarta, que posteriormente guardamos uno por uno en el array de objetos Ocarta
            for (int i = 0; i < jsoncartas.length(); ++i) {
                JSONObject object = jsoncartas.getJSONObject(i);
                titulo = object.getString("name");
                rarity = object.getString("rarity");
                colors = object.getString("colors");
                imageUrl = object.getString("imageUrl");
                types = object.getString("types");

                if(rarity.equals(rareza)) {
                    Ocarta carta = new Ocarta(titulo, types, imageUrl, colors, rarity);
                    seleccion.add(carta);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seleccion;
    }
    */
}