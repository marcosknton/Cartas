package com.example.a46453895j.cartas;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by MICHUS on 01/11/2016.
 */

public class CardsAdapter extends ArrayAdapter <Ocarta> {

    public CardsAdapter(Context context, int resource, List<Ocarta> objects){
        super(context,resource,objects);
    }
}
