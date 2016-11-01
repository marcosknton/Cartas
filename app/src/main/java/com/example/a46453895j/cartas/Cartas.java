package com.example.a46453895j.cartas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import static android.R.attr.id;

public class Cartas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.config){
            //con la clase intent  podemos abrir activities  los parametros son :
            //this hace referencia a desde donde se lanza la acitivity en este caso this xq es del mismo lugar
            //filtro.class es la activity que lanzaremos
            Intent i =new Intent(this,filtro.class);
            //arranca la actividad
            startActivity(i);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
