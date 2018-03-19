package com.example.lesh.zonaverde.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.example.lesh.zonaverde.R;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    //Variables globales
private LoginButton loginButton;
private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo para esconder la barra superior donde aparecen los iconos
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        //Llamada al metodo que inicializa los componentes
        Inicializar();
        //Permisos de facebook
        loginButton.setReadPermissions("email");
        //Callback de la interfaz de facebook
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            //Metodo de inicio de sesion correcto
            public void onSuccess(LoginResult loginResult) {

                requerirEmail(AccessToken.getCurrentAccessToken());
            }
            @Override
            //Metodo para la cancelacion del inicio de sesion
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancelado, Toast.LENGTH_SHORT).show();
            }
            @Override
            //Metodo para validar errores del inicio de sesion
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Metodo para inicializar componentes
    public void Inicializar(){
        loginButton=findViewById(R.id.login_button);
        callbackManager=CallbackManager.Factory.create();

    }
    //Metodo para redigir a otra actividad
    private void irPantallaPrincipal() {
        Intent intent =new Intent(this,BienvenidoActivity.class);
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
                    Toast.makeText(getApplicationContext(),  R.string.error, Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    //Toast.makeText(getApplicationContext(),object.getString("email"), Toast.LENGTH_SHORT).show();
                    object.get("email");
                    //Llamada al metodo por si ocurre algun error
                    irPantallaPrincipal();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),R.string.erroremail, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();//Error de email
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
    //Metodo para cerrar sesion en facebook
    private void salir() {
        LoginManager.getInstance().logOut();
    }
    //Sobreescritura del metodo del resultado de la actividad
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
