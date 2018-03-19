package com.example.lesh.zonaverde.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.lesh.zonaverde.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Variables globales
Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Metodo para esconder la barra superior donde aparecen los iconos
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Validar la sesion
        requerirEmail(AccessToken.getCurrentAccessToken());
        //Llamada al metodo qe inicializa los componentes de la vista
        Inicializar();
        //Clase anonima para el evento click del boton
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });
    }
    //Metodo que inicializa los componentes de la vista
    public void Inicializar(){
        button2=findViewById(R.id.button2);
    }
    //Metodo que llamama a la actividad para iniciar sesion
    private void irInicio() {
        Intent intent =new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    //Metodo para solicitar email al usuario y validar que lo acepte
    private void requerirEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
                    salir();
                    return;
                }
                try {
                    //Toast.makeText(getApplicationContext(),object.getString("email"), Toast.LENGTH_SHORT).show();
                    object.get("email");
                    //Llamada al metodo por si ocurre algun error

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),R.string.erroremail, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();//Error de email
                    salir();

                }
            }
        });
        //Parametros a enviar en la actividad
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        request.setParameters(parameters);
        request.executeAsync();
    }
    //Metodo para cerrar sesion de facebook
    public void salir() {
        //cerrar sesion de facebook
        //Toast.makeText(getApplicationContext(),R.string.sesioncerrada, Toast.LENGTH_SHORT).show();
        LoginManager.getInstance().logOut();
        //Llamada el metodo que abre la actividad inicio de sesion
        irInicio();
    }
}
