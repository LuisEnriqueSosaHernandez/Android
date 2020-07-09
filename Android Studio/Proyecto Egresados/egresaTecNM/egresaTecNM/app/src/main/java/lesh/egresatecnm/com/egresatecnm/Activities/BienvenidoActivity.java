package lesh.egresatecnm.com.egresatecnm.Activities;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.BienvenidoFragment;
import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.RegistroFragment;
import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.UbicacionFragment;
import lesh.egresatecnm.com.egresatecnm.Interfaces.ListenerPag;
import lesh.egresatecnm.com.egresatecnm.R;

public class BienvenidoActivity extends AppCompatActivity implements ListenerPag {
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        Inicializar();
        setFragmentByDefault();
        if (AccessToken.getCurrentAccessToken() == null){
            // irInicio();//Si no esta iniciada la sesion de faceboook, si fuera actividad principal
        } else {
            //El parametro es para saber que perfil se esta accediendo
            requerirEmail(AccessToken.getCurrentAccessToken());
            //Actualizar el perfil
           /*Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                displayProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }*/
        }

    }

    private void setFragmentByDefault(){
        fragment=new BienvenidoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_Bienvenido_frame,fragment).commit();
    }
    private void requerirEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    salir();
                    return ;
                }
                try {

                        Toast.makeText(BienvenidoActivity.this, object.getString("email"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    Toast.makeText(BienvenidoActivity.this,R.string.erroremail, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();//Error de email
                    salir();
                }
            }
        });
        //Es necesario para recuperar los datos del usuario de facebook , que solo se hara en inicioActivity
        //Con este metodo tambien validare que no la eliminen de facebook o desautoricen
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }
    public void salir() {
        //cerrar sesion de facebook
        LoginManager.getInstance().logOut();
        irInicio();
    }
    private void irInicio() {
        Intent intent =new Intent(this,inicioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void RecuperarNumPag(int num) {
        switch (num){
            case 1:
                fragment=new BienvenidoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_Bienvenido_frame,fragment).commit();
                break;
            case 2:
                fragment=new UbicacionFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_Bienvenido_frame,fragment).commit();
                break;
            case 3:
                fragment=new RegistroFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_Bienvenido_frame,fragment).commit();
                break;
            case 4:
                Intent intent =new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            default:
                fragment=new BienvenidoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_Bienvenido_frame,fragment).commit();
                break;
        }

    }

    @Override
    public void RecuperarDatos(String text) {

    }

    private void Inicializar(){
        fragment=null;
    }
}
