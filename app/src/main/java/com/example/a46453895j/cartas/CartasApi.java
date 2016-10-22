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

    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards?pageSize=50";


    public ArrayList<String> getOcartas() {

        //Uri builtUri = Uri.parse(BASE_URL).buildUpon().appendPath("name").build();

        //String url = builtUri.toString();

        final String url="https://api.magicthegathering.io/v1/cards?page=5&pageSize=100";

        ArrayList<String> lista = new ArrayList<>();

        try {
            String JsonResponse = HttpUtils.get(url);
            //creamos un objeto json y como contenido le introducimos el string resultado de la pagina
            JSONObject json = new JSONObject(JsonResponse);
            //en un variable JSONArray le asignamos el resultado de aplocar el metodo getJSONArray al objeto json
            JSONArray jsoncartas = json.getJSONArray("cards");
            String titulo;


            for (int i = 0; i < jsoncartas.length(); ++i) {
                JSONObject object = jsoncartas.getJSONObject(i);
                titulo = object.getString("name");
                lista.add(titulo);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
