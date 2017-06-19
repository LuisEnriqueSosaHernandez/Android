package com.example.pc.herramientas;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ComunicaMenu{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void menu(int queboton) {
        Intent in=new Intent(this,ActividadHerramientas.class);
        in.putExtra("BOTONPULSADO",queboton);
        startActivity(in);
    }
}
