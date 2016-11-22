package com.example.a46453895j.cartas;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;

import nl.qbusict.cupboard.Cupboard;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 46453895j on 22/11/16.
 */

public  abstract class CupboardCursorAdapter <T> extends CursorAdapter{


    public CupboardCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public CupboardCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    
}
