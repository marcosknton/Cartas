package com.example.a46453895j.cartas;

import android.net.Uri;

import java.io.IOException;

/**
 * Created by michus on 20/10/2016.
 */

public class CartasApi {

    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards?pageSize=50";

    String  getTituloCartas(){
        Uri builtUri=Uri.parse(BASE_URL).buildUpon().appendPath("name").build();
        String url=builtUri.toString();

        try {
            String JsonResponse =HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
