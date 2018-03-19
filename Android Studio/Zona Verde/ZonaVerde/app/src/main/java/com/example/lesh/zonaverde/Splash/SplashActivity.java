package com.example.lesh.zonaverde.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.lesh.zonaverde.Activities.BienvenidoActivity;
import com.example.lesh.zonaverde.Activities.LoginActivity;
import com.example.lesh.zonaverde.Activities.MainActivity;
import com.facebook.AccessToken;

public class SplashActivity extends AppCompatActivity {
    //Variables globales
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo para esconder la barra superior donde aparecen los iconos
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*Condicional para verificar que la sesion de facebook esta iniciada, y mandar a llamar
        a la actividad pertienente segun sea la respuesta*/
        if(AccessToken.getCurrentAccessToken()==null){
            intent=new Intent(this, LoginActivity.class);
        }else{
            intent=new Intent(this, BienvenidoActivity.class);
        }
        //Banderas para el intent de la actividad para evitar regresar, matar la tarea etc...
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        //Iniciar la actividad
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
