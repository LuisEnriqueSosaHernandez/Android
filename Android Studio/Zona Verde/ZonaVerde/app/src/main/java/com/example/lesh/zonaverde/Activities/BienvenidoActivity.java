package com.example.lesh.zonaverde.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lesh.zonaverde.FragmentsRegistro.BienvenidoFragment;
import com.example.lesh.zonaverde.FragmentsRegistro.FinalFragment;
import com.example.lesh.zonaverde.FragmentsRegistro.MotivosFragment;
import com.example.lesh.zonaverde.FragmentsRegistro.PoliticaFragment;
import com.example.lesh.zonaverde.FragmentsRegistro.RegistroFragment;
import com.example.lesh.zonaverde.Interfaces.Pages.EscuchandoPaginas;
import com.example.lesh.zonaverde.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

public class BienvenidoActivity extends AppCompatActivity implements EscuchandoPaginas {
    //Variables globales
private Fragment fragment;
private int pagActual=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Inicializando componentes
        Inicializar();
        //Validacion de sesion de facebook
        requerirEmail(AccessToken.getCurrentAccessToken());
        //Cambio del fragment por defecto
        setFragmentByDefault();
    }
    //Metodo para inicilizar componentes
    private void Inicializar(){
        fragment=null;
    }
    //Metodo para cambiar el fragment por defecto
    private void setFragmentByDefault(){
        fragment=new BienvenidoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
    }
    //Metodo para ir al inicio de la app
    private void irInicio() {
        Intent intent =new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    //Metodo para validar el inicio de sesion
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
    //Metodo para cerrar la sesion de facebook
    private void salir() {
        //cerrar sesion de facebook
        //Toast.makeText(getApplicationContext(),R.string.sesioncerrada, Toast.LENGTH_SHORT).show();
        LoginManager.getInstance().logOut();
        //Llamada el metodo que abre la actividad inicio de sesion
        irInicio();
    }
    //Metodo de la interfaz para escuchar los fragments
    @Override
    public void RecuperarNumPag(int num) {
        pagActual=num;
        //Switch para cambiar entre fragments
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
                fragment=new MotivosFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 4:
                fragment=new RegistroFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 5:
                fragment=new FinalFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 6:
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
    //Metodo para cambiar el evento del boton nativo atras
    @Override
    public void onBackPressed() {
        if(pagActual>0) {
            pagActual -= 1;
        }
        switch (pagActual){
            case 1:
                fragment=new BienvenidoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 2:
                fragment=new PoliticaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 3:
                fragment=new MotivosFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 4:
                fragment=new RegistroFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 5:
                fragment=new FinalFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,fragment).commit();
                break;
            case 6:
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
