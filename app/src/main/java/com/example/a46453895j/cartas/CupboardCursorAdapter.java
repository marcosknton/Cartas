package com.example.a46453895j.cartas;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import nl.qbusict.cupboard.Cupboard;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 46453895j on 22/11/16.
 */

public  abstract class CupboardCursorAdapter <T> extends CursorAdapter{


    private final Cupboard mCupboard;
    private Class<T> mEntityClass;

    public CupboardCursorAdapter(Context context, Class<T> entityClass) {
                this(context, cupboard(), entityClass, null);
    }

    public CupboardCursorAdapter(Context context, Cupboard cupboard, Class<T> entityClass) {
        this(context, cupboard, entityClass, null);
    }

    public CupboardCursorAdapter(Context context, Cupboard cupboard, Class<T> entityClass, Cursor cursor) {
        super(context, cursor, false);
        this.mEntityClass = entityClass;
        this.mCupboard = cupboard;
    }




    public abstract View newView(Context context, T model, ViewGroup parent);


    public abstract void bindView(View view, Context context, T model);
    //crea una vista nueva con los elementos , llamará a tantas veces como elementos quepan en la pantalla
    @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return newView(context, getItem(cursor.getPosition()), parent);
        }
    //recicla la vistacreada en el newview
    @Override
        public void bindView(View view, Context context, Cursor cursor) {
                bindView(view, context, getItem(cursor.getPosition()));
        }

    //obtiene la posicion del cursor para reciclar la pantalla
    public T getItem(int position) {
                if (getCursor().moveToPosition(position)) {
                        return mCupboard.withCursor(getCursor()).get(mEntityClass);
                }
                else {
                    throw new IllegalArgumentException("Invalid position: " + position);
                }
    }
}