package com.example.pc.pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ImageView img1=(ImageView)findViewById(R.id.icono_edicion);
        img1.setImageResource(android.R.drawable.ic_menu_edit);*/
        TextView txt1=(TextView)findViewById(R.id.texto_cancelar);
        txt1.setText(android.R.string.cancel);
    }
}
