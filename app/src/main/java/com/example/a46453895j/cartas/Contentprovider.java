package com.example.a46453895j.cartas;


import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

//content provider creación y acceso a la basededatos
public class Contentprovider  extends CupboardContentProvider{
    // The content provider authority se usa para construir las  Uri's para el provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static{
        cupboard().register(Ocarta.class);
    }

    public Contentprovider(){
        super(AUTHORITY,1);
    }

}
