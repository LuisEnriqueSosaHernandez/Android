package com.example.pc.contador;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    public int contador;
    TextView txtresultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtresultado=(TextView)findViewById(R.id.contadorTexto);
        contador=0;
        txtresultado.setText(""+contador);
        EventoTeclado teclado=new EventoTeclado();
        EditText n_reseteo=(EditText)findViewById(R.id.n_reseteo);
        n_reseteo.setOnEditorActionListener(teclado);

    }
    //Metodos bundle
/*    public void onSaveInstanceState(Bundle estado){
        estado.putInt("cuenta", contador);
        super.onSaveInstanceState(estado);
    }
    public void onRestoreInstanceState(Bundle estado){
        super.onRestoreInstanceState(estado);
        contador=estado.getInt("cuenta");
        txtresultado.setText(""+contador);
    }*/
    //Metodos shared preference

    public void onPause(){
        super.onPause();
        SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor=datos.edit();
        miEditor.putInt("cuenta",contador);
        miEditor.apply();
    }
    public void onResume(){
        super.onResume();
        SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(this);
       contador= datos.getInt("cuenta",0);
        txtresultado.setText(""+contador);
    }
    class EventoTeclado implements  TextView.OnEditorActionListener{


        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId== EditorInfo.IME_ACTION_DONE){
                resetea(null);
            }

            return false;
        }
    }
    public void incrementar(View vista){
        contador++;
       txtresultado.setText(""+contador);
    }
    public void resta(View vista){

        contador--;
        if(contador<0){
            CheckBox negativos=(CheckBox)findViewById(R.id.negativos);
            if(!negativos.isChecked()){
                contador=0;
            }
        }
        txtresultado.setText(""+contador);
    }
    public void resetea(View vista){
        EditText numero_reset = (EditText) findViewById(R.id.n_reseteo);
        try {
            contador = Integer.parseInt(numero_reset.getText().toString());

        }catch (Exception e){
            contador = 0;
        }
        txtresultado.setText("" + contador);
        InputMethodManager miteclado=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        miteclado.hideSoftInputFromWindow(numero_reset.getWindowToken(),0);
    }

    /*public void mostrar(){
        TextView textoResultado=(TextView)findViewById(R.id.contadorTexto);
        textoResultado.setText(""+contador);
    }*/

}

