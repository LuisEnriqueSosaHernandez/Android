package com.example.lesh.zonaverde.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lesh.zonaverde.FragmentsRegistro.BienvenidoFragment;
import com.example.lesh.zonaverde.FragmentsRegistro.PoliticaFragment;
import com.example.lesh.zonaverde.Interfaces.Pages.EscuchandoPaginas;
import com.example.lesh.zonaverde.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

public class BienvenidoActivity extends AppCompatActivity implements EscuchandoPaginas {
private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Inicializar();
        requerirEmail(AccessToken.getCurrentAccessToken());
        setFragmentByDefault();
    }

    private void Inicializar(){
        fragment=null;
    }

    private void setFragmentByDefault(){
        fragment=new BienvenidoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
    }

    private void irInicio() {
        Intent intent =new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

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

    private void salir() {
        //cerrar sesion de facebook
        //Toast.makeText(getApplicationContext(),R.string.sesioncerrada, Toast.LENGTH_SHORT).show();
        LoginManager.getInstance().logOut();
        //Llamada el metodo que abre la actividad inicio de sesion
        irInicio();
    }

    @Override
    public void RecuperarNumPag(int num) {
        switch (num){
            case 1:
                fragment=new BienvenidoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 2:
                fragment=new PoliticaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 3:

            case 4:
                Intent intent =new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            default:
                fragment=new BienvenidoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
        }
    }
}
