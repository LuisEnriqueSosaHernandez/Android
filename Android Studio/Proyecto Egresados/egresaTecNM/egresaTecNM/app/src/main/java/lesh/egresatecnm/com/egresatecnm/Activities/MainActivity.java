package lesh.egresatecnm.com.egresatecnm.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;


import org.json.JSONException;
import org.json.JSONObject;
import lesh.egresatecnm.com.egresatecnm.FragmentEmpleos.EmpleoFragment;
import lesh.egresatecnm.com.egresatecnm.FragmentEmpleos.EmpleosFragment;
import lesh.egresatecnm.com.egresatecnm.FragmentEncuesta.ComienzoFragment;
import lesh.egresatecnm.com.egresatecnm.FragmentEncuesta.Seccion1Fragment;
import lesh.egresatecnm.com.egresatecnm.FragmentPerfil.PerfilFragment;
import lesh.egresatecnm.com.egresatecnm.FragmentPolitica.PoliticaFragment;
import lesh.egresatecnm.com.egresatecnm.Interfaces.ListenerPag;
import lesh.egresatecnm.com.egresatecnm.R;
public class MainActivity extends AppCompatActivity  implements ListenerPag {
    //private ProfileTracker profileTracker;
    //--private TextView txtemail;
    //--private Button salir;
    TextView txtCorreoHeader;
    View hView;
    Fragment fragment;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;
   ImageView ImgPerfilMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inicializar();
        setToolbar();
        setFragmentByDefault();
            //Toast.makeText(this, "It is empty!", Toast.LENGTH_SHORT).show();
        //Para seguir rastreando el  perfil por algun cambio
        /*profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

                if (currentProfile != null) {
                    displayProfileInfo(currentProfile);

                }
            }
        };*/
        //Mejor lo valide en el splash para que la carga fuera mas rapida
        //Validar que este iniciada la sesion
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
        /*--salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });*/
            setImage();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean  fragmentTransaction=false;
                switch(item.getItemId()){
                    case R.id.menu_perfil:
                        fragment=new PerfilFragment();
                        fragmentTransaction=true;
                        break;
                    case R.id.menu_encuesta:
                        fragment=new ComienzoFragment();
                        fragmentTransaction=true;
                        break;
                    case R.id.menu_empleos:
                        fragment=new EmpleosFragment();
                        fragmentTransaction=true;
                        break;
                    case R.id.menu_politica:
                        fragment=new PoliticaFragment();
                        fragmentTransaction=true;
                        break;
                    case R.id.menu_salir:
                       salir();
                        break;
                    default:
                        fragment=new PerfilFragment();
                        fragmentTransaction=true;
                        break;

                }
                if(fragmentTransaction){
                    changeFragment(fragment,item);
                    drawerLayout.closeDrawers();

                }
                return true;
            }
        });

    }
    private void Inicializar(){
        //--txtemail=(TextView) findViewById(R.id.txtEmail);
        //--salir=(Button) findViewById(R.id.salir);
        fragment=null;
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.navview);
        hView = navigationView.getHeaderView(0);
        txtCorreoHeader=(TextView) hView.findViewById(R.id.txtCorreoHeader);
        ImgPerfilMenu=(ImageView)hView.findViewById(R.id.ImgPerfilMenu);
    }
    //Requerir el email, esto solo lo requerire una vez y guardare en base de datos o en un archivo shared pero en inicio esto es
    //para recuperalo aqui y mostrarlo
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
                    String email = object.getString("email");
                   setEmail(email);

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this,R.string.erroremail, Toast.LENGTH_SHORT).show();
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

    private void setEmail(String email) {
        txtCorreoHeader.setText(email);
    }
    private void setImage(){
        Drawable originalDrawable = getResources().getDrawable(R.drawable.perfil);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());
        ImgPerfilMenu.setImageDrawable(roundedDrawable);
    }
    private void irInicio() {
        Intent intent =new Intent(this,inicioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void salir() {
        //cerrar sesion de facebook
        LoginManager.getInstance().logOut();
        irInicio();
    }
    //Para actualizar el perfil con facebook

    /*private void displayProfileInfo(Profile profile) {

    }*/
    private void setToolbar(){
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setToolbar2(int id,String titulo){
        getSupportActionBar().setHomeAsUpIndicator(id);
        getSupportActionBar().setTitle(titulo);

    }
    private void setFragmentByDefault(){
        changeFragment(new PerfilFragment(),navigationView.getMenu().getItem(0));
    }
    private void changeFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                //abrir el menu lateral
                if(getSupportActionBar().getTitle().toString().equals("Empleo")){
                    fragment=new EmpleosFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
                    setToolbar2(R.drawable.ic_menu,"Ofertas de empleo");
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //destruir el perfil tracking
      //  profileTracker.stopTracking();
    }

    @Override
    public void RecuperarNumPag(int num) {
        switch (num){
            case 0:
                fragment=new ComienzoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
                break;
            case 1:
                fragment=new Seccion1Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
                break;
            default:
                fragment=new ComienzoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
                break;
        }
    }

    @Override
    public void RecuperarDatos(String Empleo) {
        Bundle Datos=new Bundle();
        Datos.putString("Empleo",Empleo);
        fragment=new EmpleoFragment();
        fragment.setArguments(Datos);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
        setToolbar2(R.drawable.ic_back,"Empleo");
    }
}
