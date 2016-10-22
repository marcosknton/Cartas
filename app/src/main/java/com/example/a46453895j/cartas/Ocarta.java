package com.example.a46453895j.cartas;

import java.util.ArrayList;

/**
 * Created by michus on 19/10/2016.
 */

public class Ocarta {

    private String titulo;


    public Ocarta(String titulo) {
        this.titulo=titulo;
    }




    public void setTitulo(String titulo) {
       this.titulo=titulo;
    }



    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String toString() {
        return "Carta{"+"titulo="+titulo+"}";
    }
}
