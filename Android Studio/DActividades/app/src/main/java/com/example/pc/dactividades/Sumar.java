package com.example.pc.dactividades;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by pc on 25/07/2016.
 */
public class Sumar extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        Bundle datos=getIntent().getExtras();

        int num1=datos.getInt("numero1");
        int num2=datos.getInt("numero2");

        int resultado=num1+num2;

        TextView valor_resultado=(TextView)findViewById(R.id.texto_resultado);
        valor_resultado.setText("El resultado es: "+resultado);

    }
}
