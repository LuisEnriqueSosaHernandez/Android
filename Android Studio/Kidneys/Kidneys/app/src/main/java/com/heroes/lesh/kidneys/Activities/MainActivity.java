package com.heroes.lesh.kidneys.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.FragmentsAbout.AboutFragment;
import com.heroes.lesh.kidneys.FragmentsContact.ContactFragment;
import com.heroes.lesh.kidneys.FragmentsContact.ContactMessageFragment;
import com.heroes.lesh.kidneys.FragmentsFollow.FollowFragment;
import com.heroes.lesh.kidneys.FragmentsFollow.FragmentsFollowFollow.FollowFollowDayFragment;
import com.heroes.lesh.kidneys.FragmentsFollow.FragmentsFollowFollow.FollowFollowDayHourFragment;
import com.heroes.lesh.kidneys.FragmentsFollow.FragmentsFollowFollow.FollowFollowsFragment;
import com.heroes.lesh.kidneys.FragmentsFoodRules.FragmentsFoodRulesFoods.FoodRulesFoodFragment;
import com.heroes.lesh.kidneys.FragmentsFoodRules.FragmentsFoodRulesFoods.FoodRulesFoodsFragment;
import com.heroes.lesh.kidneys.FragmentsFoodRules.FragmentsFoodRulesRecipes.FoodRulesRecipeFragment;
import com.heroes.lesh.kidneys.FragmentsFoodRules.FragmentsFoodRulesRecipes.FoodRulesRecipesFragment;
import com.heroes.lesh.kidneys.FragmentsFoodRules.FoodRulesFragment;
import com.heroes.lesh.kidneys.FragmentsHelp.FragmentsHelpQuestions.HelpQuestionFragment;
import com.heroes.lesh.kidneys.FragmentsHelp.FragmentsHelpQuestions.HelpQuestionsFragment;
import com.heroes.lesh.kidneys.FragmentsHelp.HelpFragment;
import com.heroes.lesh.kidneys.FragmentsMain.MainFragment;
import com.heroes.lesh.kidneys.FragmentsMaps.MapFragment;
import com.heroes.lesh.kidneys.FragmentsPrivacyPolitic.PrivacyPoliticFragment;
import com.heroes.lesh.kidneys.FragmentsProfile.ProfileFragment;
import com.heroes.lesh.kidneys.FragmentsProfile.ProfilePasswordFragment;
import com.heroes.lesh.kidneys.FragmentsProfile.ProfileUpdateFragment;
import com.heroes.lesh.kidneys.FragmentsSettings.SettingsFragment;
import com.heroes.lesh.kidneys.FragmentsTreatments.FragmentsTreatmentsGuides.TreatmentsGuideFragment;
import com.heroes.lesh.kidneys.FragmentsTreatments.TreatmentsFragment;
import com.heroes.lesh.kidneys.FragmentsTreatments.FragmentsTreatmentsGuides.TreatmentsGuidesFragment;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.Follow;
import com.heroes.lesh.kidneys.Models.Food;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuideApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserApi;
import com.heroes.lesh.kidneys.Models.Recipe;
import com.heroes.lesh.kidneys.R;

import com.facebook.login.LoginManager;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ListenerPag {
    //Variables globales
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private Intent intent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private CircularImageView navPerfilImg;
    private View hView;
    private TextView navPerfilName;
    private TextView navPerfilTitle;
    @BindView(R.id.navvView)
    NavigationView navvView;
    @BindDrawable(R.drawable.ic_home)
    Drawable ic_home;
    @BindDrawable(R.drawable.ic_back)
    Drawable ic_back;
    @BindDrawable(R.drawable.loading)
    Drawable loading;
    @BindString(R.string.errorconexion)
    String errorconexion;
    @BindString(R.string.errorsesion)
    String errorsesion;
    @BindString(R.string.sesioncerrada)
    String sesioncerrada;
    @BindString(R.string.inicio)
    String inicio;
    @BindString(R.string.perfil)
    String perfil;
    @BindString(R.string.tratamientos)
    String tratamientos;
    @BindString(R.string.reglas)
    String reglas;
    @BindString(R.string.mapa)
    String mapa;
    @BindString(R.string.contacto)
    String contacto;
    @BindString(R.string.politicas)
    String politicas;
    @BindString(R.string.acercade)
    String acercade;
    @BindString(R.string.preguntasfrecuentes)
    String preguntasfrecuentes;
    @BindString(R.string.salir)
    String salir;
    @BindString(R.string.recetas)
    String recetas;
    @BindString(R.string.guias)
    String guias;
    @BindString(R.string.guia)
    String guia;
    @BindString(R.string.alimentos)
    String alimentos;
    @BindString(R.string.registro)
    String registro;
    @BindString(R.string.seguimientos)
    String seguimientos;
    @BindString(R.string.seguimiento)
    String seguimiento;
    @BindString(R.string.ayuda)
    String ayuda;
    @BindString(R.string.ajustes)
    String ajustes;
    @BindString(R.string.cambiarcontra)
    String cambiarcontra;
    @BindString(R.string.noregistro)
    String noregistro;
    @BindString(R.string.errorraro)
    String errorraro;
    @BindString(R.string.bienvenidootra)
    String bienvenidootra;
    @BindString(R.string.redactarcorreo)
    String redactarcorreo;
    @BindString(R.string.cambiosrealizados)
    String cambiosrealizados;
    @BindString(R.string.actualizarperfil)
    String actualizarperfil;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.mipmap.ic_settings)
    Drawable ic_settings;
    @BindDrawable(R.mipmap.ic_profile)
    Drawable ic_profile;
    @BindDrawable(R.mipmap.ic_cerrarsesion)
    Drawable ic_cerrarsesion;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    ArrayList<String> titulos;
    private String correo;
    private String name;
    private String picture;
    private String genero;
    private String dateOfBirthday;
    private double weight;
    private double height;
    private String dateCatheter;
    private double typeOfSolution;
    private double imc;
    private String typeOfTreatment;
    private String emergencyContact;
    private boolean fragmentTransaction;
    private Fragment fragment;
    private FragmentTransaction transaction;
    private FragmentManager pila;
    private Bundle bundle;
    private Bundle bundleTablas;
    private SharedPreferences preferences;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private UserApi userApi;
    private KidneysService kidneysService;
    private String jsonFoods;
    private String jsonRecipes;
    private String jsonFollows;
    private String jsonQuestions;
    private String jsonLocations;
    private String jsonGuides;
    private String jsonUser;
    private String jsonFollowsDay;
    private int color;
    private Gson gson;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Controlar la perdida de informacion con rotacion con este metodo y poner rotacion
        /*if(savedInstanceState==null){
            currentFragment=new WelcomeFragment();
            changeFragment(currentFragment);
        }*/
        setContentView(R.layout.activity_main);
        //Poner la actividad en pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        Inicializar();
        //Cargar tablas de  la bd aun no lo usare, lo cambiare
        //Inicializar el toast
        inicializarToast();
        //Recuperar correo
        correoPreferences();
        //Cargar las tablas
        cargarTablas();
        //Cambiar colores de preferencias
        changeColors();
        //Metodo para pintar el toolbar
        setToolbar();
        //Cambiar navvview
        setNavView();
        //Cambiar fragment por defecto
        setFragmentByDefault();
        //navigation view
        navvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                fragmentTransaction = false;
                //Borrrar pila completa por si algo raro pasa
                if (transaction.isAddToBackStackAllowed()) {
                    pila.popBackStackImmediate(null, pila.POP_BACK_STACK_INCLUSIVE);
                    titulos.clear();
                    getSupportActionBar().setHomeAsUpIndicator(ic_home);
                }
                fragment = null;
                switch (item.getItemId()) {
                    case R.id.navinicio:
                        fragment = new MainFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.navperfil:
                        bundle = new Bundle();
                        fragment = new ProfileFragment();
                        if (correo != null) {
                            if (picture != null && name != null && genero != null&&dateOfBirthday!=null
                                    &&dateCatheter!=null&&typeOfTreatment!=null&&emergencyContact!=null) {
                                bundle.putString("Picture", picture);
                                bundle.putString("Correo", correo.trim());
                                bundle.putString("Name", name.trim());
                                bundle.putString("Genero", genero.trim());
                                bundle.putString("DateOfBirthday",dateOfBirthday.trim());
                                bundle.putDouble("Weight",weight);
                                bundle.putDouble("Height",height);
                                bundle.putString("DateCatheter",dateCatheter);
                                bundle.putDouble("TypeOfSolution",typeOfSolution);
                                bundle.putDouble("Imc",imc);
                                bundle.putString("TypeOfTreatment",typeOfTreatment);
                                bundle.putString("EmergencyContact",emergencyContact);
                                fragment.setArguments(bundle);
                            }
                        }
                        fragmentTransaction = true;
                        break;
                    case R.id.navtratamiento:
                        fragment = new TreatmentsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.navreglas:
                        fragment = new FoodRulesFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.navmapa:
                        fragment = new MapFragment();
                        if (jsonLocations != null) {
                            bundle = new Bundle();
                            bundle.putString("Locations", jsonLocations);
                            fragment.setArguments(bundle);
                        }
                        fragmentTransaction = true;
                        break;
                    case R.id.navseguimiento:
                        fragment = new FollowFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.navcontacto:
                        fragment = new ContactFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.navpoliticas:
                        fragment = new PrivacyPoliticFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.navacercade:
                        fragment = new AboutFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.navhelp:
                        fragment = new HelpFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.navsalir:
                        salir(sesioncerrada, ic_cerrarsesion);
                        break;
                    default:
                        break;
                }
                if (fragmentTransaction) {
                    changeFragment(fragment, item);
                    drawerLayout.closeDrawers();
                    //Checar lo de la animacion drawerLayout.animate().alpha(1).setDuration(500).start();
                }
                return true;
            }
        });
    }

    //Metodo para cargar las tablas
    private void cargarTablas() {
        bundleTablas = getIntent().getExtras();
        if (bundleTablas != null) {//No llegara nulo por que es main a lo mejor o activity
            jsonFoods = bundleTablas.getString("Foods");
            jsonRecipes = bundleTablas.getString("Recipes");
            jsonFollows = bundleTablas.getString("Follows");
            jsonQuestions = bundleTablas.getString("Questions");
            jsonLocations = bundleTablas.getString("Locations");
            jsonGuides = bundleTablas.getString("Guides");
            jsonFollowsDay=bundleTablas.getString("FollowsDays");
            jsonUser=bundleTablas.getString("User");
                if (jsonUser != null) {
                    userApi = gson.fromJson(jsonUser, UserApi.class);
                    correo = userApi.getEmail();
                    name = userApi.getName();
                    genero = userApi.getGender();
                    picture = userApi.getImage();
                    dateOfBirthday=userApi.getDateOfBirth();
                    weight=userApi.getWeight();
                    height=userApi.getHeight();
                    dateCatheter=userApi.getDateCatheter();
                    typeOfSolution=userApi.getTypeOfSolution();
                    imc=userApi.getImc();
                    typeOfTreatment=userApi.getTypeOfTreatment();
                    emergencyContact=userApi.getEmergencycontact();
                    phoneEmergencySave(emergencyContact);
                    setDatos();
                } else {
                    tryAgainCargarTablaUser();
                }
        }
    }
//Metodo para volver a cargar el usuario
    private void tryAgainCargarTablaUser(){
        selectUser(correo);
    }
    //Metodo para recuperar el correo de las shared
    private void correoPreferences() {
        correo = preferences.getString("Email", "");
        if (TextUtils.equals(correo, "") || TextUtils.equals(correo, "no email")) {
            salir(errorsesion, ic_harmful);
        }
    }


    //Metodo para modificar el toolbar
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Metodo para inflar el menu en el action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_options_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Sobreescribimos el metodo de option item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //abrir el menu lateral
                if (home()) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    pila.popBackStack();
                    controlBack();
                }
                break;
            case R.id.item_settings:
                if (getSupportActionBar().getTitle() != ajustes) {
                    fragment = new SettingsFragment();
                    transaction = getSupportFragmentManager().beginTransaction().
                            setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                            replace(R.id.content_frame, fragment).addToBackStack(null);
                    transaction.commit();
                    controlGO(ajustes);
                }
                break;
            //Validar el regreso del boton
        }
        return super.onOptionsItemSelected(item);
    }

    //Metodo para saber si es o no el icono home
    private boolean home() {
        if (getSupportActionBar().getTitle() == inicio ||
                getSupportActionBar().getTitle() == perfil ||
                getSupportActionBar().getTitle() == tratamientos ||
                getSupportActionBar().getTitle() == reglas ||
                getSupportActionBar().getTitle() == mapa ||
                getSupportActionBar().getTitle() == contacto ||
                getSupportActionBar().getTitle() == politicas ||
                getSupportActionBar().getTitle() == acercade ||
                getSupportActionBar().getTitle() == ayuda ||
                getSupportActionBar().getTitle() == seguimiento ||
                getSupportActionBar().getTitle() == salir) {
            return true;
        } else {
            return false;
        }
    }





    //Metodo para cambiar el fragment por defecto
    private void setFragmentByDefault() {
        changeFragment(new MainFragment(), navvView.getMenu().getItem(0));
    }

    //Metodo para cambiar fragments
    private void changeFragment(Fragment fragment, MenuItem item) {
        transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                replace(R.id.content_frame, fragment);
        transaction.commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    //Metodo para dejar colores oroiginales iconos navview
    private void setNavView() {
        navvView.setItemIconTintList(null);
    }

    //Metodo para inicializar componentes
    private void Inicializar() {
        hView = navvView.getHeaderView(0);
        navPerfilImg = hView.findViewById(R.id.navPerfilImg);
        navPerfilName = hView.findViewById(R.id.navPerfilName);
        navPerfilTitle = hView.findViewById(R.id.navPerfilTitle);
        pila = getSupportFragmentManager();
        titulos = new ArrayList<String>();
        kidneysService = Api.getApi().create(KidneysService.class);
        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();
    }

    //Metodo para inicializar el toast a renderizar
    private void inicializarToast() {
        layoutInflater = getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    //Metodo para cambiar los parametros al toast
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para recuperar el color requerido
    private void changeColors() {
        color = preferences.getInt("ColorApp", 1);
        switch (color) {
            case 1:
                setColorsApp(colorAzul);
                break;
            case 2:
                setColorsApp(colorMarron);
                break;
            case 3:
                setColorsApp(colorRosado);
                break;
        }
    }

    //Metodo para cambiar los colores de la app
    private void setColorsApp(int color) {
        toolbar.setBackgroundColor(color);
        navPerfilTitle.setBackgroundColor(color);
        hView.setBackgroundColor(color);
    }

    //Metodo para cambiar a la pantalla de inicio de sesion
    private void irLogin() {
        intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //Quitar animacion de transicion
        overridePendingTransition(0, 0);
    }


    //Metodo para recuperar el usuario de la api por si quita permisos en facebook
    private void selectUser(String correoApi) {
        kidneysService.selectUserApi(correoApi).enqueue(new retrofit2.Callback<UserApi>() {
            @Override
            public void onResponse(Call<UserApi> call, Response<UserApi> response) {
                userApi = response.body();
                if (userApi==null) {
                    salir(errorsesion,ic_harmful);
                } else {
                    correo = userApi.getEmail();
                    name = userApi.getName();
                    genero = userApi.getGender();
                    picture = userApi.getImage();
                    dateOfBirthday=userApi.getDateOfBirth();
                    weight=userApi.getWeight();
                    height=userApi.getHeight();
                    dateCatheter=userApi.getDateCatheter();
                    typeOfSolution=userApi.getTypeOfSolution();
                    imc=userApi.getImc();
                    typeOfTreatment=userApi.getTypeOfTreatment();
                    emergencyContact=userApi.getEmergencycontact();
                    phoneEmergencySave(emergencyContact);
                    jsonUser = gson.toJson(userApi);
                    setDatos();
                }
            }

            @Override
            public void onFailure(Call<UserApi> call, Throwable t) {
                changeToast(errorconexion, ic_harmful);
                toast.show();
            }
        });
    }

    //Metodo para cambiar los datos del usuario en el navigation view
    private void setDatos() {
        navPerfilName.setText(name.trim());
        Picasso.get().load(picture).placeholder(loading).into(navPerfilImg, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.profile).placeholder(loading).into(navPerfilImg);
            }
        });
    }

    //Metodo para cerrar sesion en facebook
    private void salir(String message, Drawable icon) {
        changeToast(message, icon);
        toast.show();
        //cierre de sesion
        LoginManager.getInstance().logOut();
        deleteEmail();
        //Llamada al metodo de retorno a inicio de sesion
        irLogin();
    }

    //Metodo para eliminar el email de las preferencias y asi evitar carga de datos excesiva
    private void deleteEmail() {
        editor.putString("Email", "no email");
        editor.apply();
    }
    private void phoneEmergencySave(String emergencyContact){
        editor.putString("PhoneEmergency", emergencyContact);
        editor.apply();
    }

    //Metodo implementando con la interfaz para cambiar fragments dentro de fragments
    @Override
    public void openFragment(int num) {
        //addtoback stack(null) con una instancia de transaction fragment para habilitar el boton atras
        switch (num) {
            case 1:
                fragment = new TreatmentsGuidesFragment();
                if (jsonGuides != null) {
                    bundle = new Bundle();
                    bundle.putString("Guides", jsonGuides);
                    fragment.setArguments(bundle);
                }
                transaction = getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.content_frame, fragment).addToBackStack(null);
                transaction.commit();
                controlGO(guias);
                break;
            case 3:
                fragment = new FoodRulesRecipesFragment();
                if (jsonRecipes != null) {
                    bundle = new Bundle();
                    bundle.putString("Recipes", jsonRecipes);
                    fragment.setArguments(bundle);
                }
                transaction = getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.content_frame, fragment).addToBackStack(null);
                transaction.commit();
                controlGO(recetas);
                break;
            case 4:
                fragment = new FoodRulesFoodsFragment();
                if (jsonFoods != null) {
                    bundle = new Bundle();
                    bundle.putString("Foods", jsonFoods);
                    fragment.setArguments(bundle);
                }
                transaction = getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.content_frame, fragment).addToBackStack(null);
                transaction.commit();
                controlGO(alimentos);
                break;
            case 6:
                fragment = new FollowFollowsFragment();
                bundle = new Bundle();
                if (jsonFollows != null) {
                    bundle.putString("Follows", jsonFollows);
                }
                bundle.putString("Email", correo);
                fragment.setArguments(bundle);
                transaction = getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.content_frame, fragment).addToBackStack(null);
                transaction.commit();
                controlGO(seguimientos);
                break;
            case 8:
                fragment = new ContactMessageFragment();
                transaction = getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.content_frame, fragment).addToBackStack(null);
                transaction.commit();
                controlGO(redactarcorreo);
                break;
            case 11:
                fragment = new HelpQuestionsFragment();
                if (jsonQuestions != null) {
                    bundle = new Bundle();
                    bundle.putString("Questions", jsonQuestions);
                    fragment.setArguments(bundle);
                }
                transaction = getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.content_frame, fragment).addToBackStack(null);
                transaction.commit();
                controlGO(preguntasfrecuentes);
                break;
            case 12:
                fragment = new ProfilePasswordFragment();
                transaction = getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.content_frame, fragment).addToBackStack(null);
                transaction.commit();
                controlGO(cambiarcontra);
                break;
            case 13:
                fragment = new ProfileUpdateFragment();
                if (correo != null) {
                    if (name != null && genero != null&&dateOfBirthday!=null&&dateCatheter!=null&&typeOfTreatment!=null&&emergencyContact!=null) {
                        bundle.putString("Name", name.trim());
                        bundle.putString("Genero", genero.trim());
                        bundle.putString("DateOfBirthday",dateOfBirthday.trim());
                        bundle.putDouble("Weight",weight);
                        bundle.putDouble("Height",height);
                        bundle.putString("DateCatheter",dateCatheter);
                        bundle.putDouble("TypeOfSolution",typeOfSolution);
                        bundle.putDouble("Imc",imc);
                        bundle.putString("TypeOfTreatment",typeOfTreatment);
                        bundle.putString("EmergencyContact",emergencyContact);
                        fragment.setArguments(bundle);
                    }
                }
                transaction = getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.content_frame, fragment).addToBackStack(null);
                transaction.commit();
                controlGO(actualizarperfil);
                break;
        }
    }


    //Metodo para controlar el abrir de los fragments para cmabiar titulos y a;adir a la pila
    private void controlGO(String title) {
        getSupportActionBar().setHomeAsUpIndicator(ic_back);
        titulos.add(getSupportActionBar().getTitle().toString());
        getSupportActionBar().setTitle(title);
    }

    //Metodo para controlar el regreso de los fragments cambiar titulos y quitar de la pila
    private void controlBack() {
        if (titulos.size() != 0) {
            getSupportActionBar().setTitle(titulos.get(titulos.size() - 1));
            titulos.remove(titulos.size() - 1);
            if (titulos.size() == 0) {
                getSupportActionBar().setHomeAsUpIndicator(ic_home);
            }
        }
    }


    //Metodo llamado al destruir la actividad
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void closeViewPagerAndOpenActivity() {

    }

    //Metodo para enviar la receta
    @Override
    public void sendRecipe(Recipe recipe) {
        bundle = new Bundle();
        bundle.putString("Image", recipe.getImage());
        bundle.putString("Title", recipe.getTitle());
        bundle.putString("Description", recipe.getDescription());
        bundle.putString("Ingredients", recipe.getIngredients());
        bundle.putString("Prepare", recipe.getPrepare());
        bundle.putString("HealthRangeName", recipe.getHealthRangeName());
        bundle.putString("Porcion",recipe.getPortion());
        bundle.putString("Sodio",recipe.getSodium());
        bundle.putString("Potasio",recipe.getPotassium());
        bundle.putString("Fosforo",recipe.getPhosphor());
        fragment = new FoodRulesRecipeFragment();
        fragment.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                replace(R.id.content_frame, fragment).addToBackStack(null);
        transaction.commit();
        controlGO(recipe.getTitle());
    }

    //Metodopara enviar el follow
    @Override
    public void sendFollow(Follow follow) {
        bundle = new Bundle();
        if (jsonFollowsDay != null) {
            bundle.putString("FollowsDays", jsonFollowsDay);
        }
        bundle.putInt("IdFollow", follow.getIdFollow());
        bundle.putString("HealthRangeName", follow.getHealthRangeName());
        bundle.putString("Email", correo);
        fragment = new FollowFollowDayFragment();
        fragment.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                replace(R.id.content_frame, fragment).addToBackStack(null);
        transaction.commit();
        controlGO(follow.getDate());
    }

    //Metodo para enviar el food
    @Override
    public void sendFood(Food food) {
        bundle = new Bundle();
        bundle.putString("Image", food.getImage());
        bundle.putString("Name", food.getName());
        bundle.putString("Description", food.getDescription());
        bundle.putString("Category", food.getCategory());
        bundle.putString("HealthRangeName", food.getHealthRangeName());
        bundle.putString("Porcion",food.getPortion());
        bundle.putString("Sodio",food.getSodium());
        bundle.putString("Potasio",food.getPotassium());
        bundle.putString("Fosforo",food.getPhosphor());
        fragment = new FoodRulesFoodFragment();
        fragment.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                replace(R.id.content_frame, fragment).addToBackStack(null);
        transaction.commit();
        controlGO(food.getName());
    }

    //Metodo para enviar la question
    @Override
    public void sendQuestion(QuestionApi questionApi) {
        bundle = new Bundle();
        bundle.putInt("IdQuestion", questionApi.getIdQuestion());
        bundle.putString("Question", questionApi.getQuestion());
        bundle.putString("Answer", questionApi.getAnswer());
        fragment = new HelpQuestionFragment();
        fragment.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                replace(R.id.content_frame, fragment).addToBackStack(null);
        transaction.commit();
        controlGO(questionApi.getQuestion());
    }

    @Override
    public void sendGuide(GuideApi guideApi) {
        bundle=new Bundle();
        bundle.putInt("IdGuide",guideApi.getIdGuide());
        bundle.putString("Name",guideApi.getName());
        bundle.putString("Description",guideApi.getDescription());
        bundle.putInt("NumPages",guideApi.getNumPages());
        bundle.putString("Image",guideApi.getImage());
        bundle.putString("Pdf",guideApi.getPdf());
        bundle.putString("Author",guideApi.getAuthor());
        bundle.putString("YearPublication",guideApi.getYearPublication());
        bundle.putString("Reference",guideApi.getReference());
        bundle.putString("Content",guideApi.getContent());
        fragment = new TreatmentsGuideFragment();
        fragment.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                replace(R.id.content_frame, fragment).addToBackStack(null);
        transaction.commit();
        controlGO(guideApi.getName());
    }

    @Override
    public void sendFollowDay(FollowsDayApi followsDayApi) {
        fragment = new FollowFollowDayHourFragment();
        bundle=new Bundle();
       // bundle.putInt("IdFollowDay",followsDayApi.getIdFollowDay());
        bundle.putString("Start",followsDayApi.getStart());
        bundle.putString("End",followsDayApi.getEnd());
        bundle.putDouble("TypeOfSolution",followsDayApi.getTypeOfSolution());
        bundle.putDouble("Drainage",followsDayApi.getDrainage());
        bundle.putDouble("Uf",followsDayApi.getUf());
        bundle.putDouble("IngestedLiquid",followsDayApi.getIngestedLiquid());
        //bundle.putInt("IdFollow",followsDayApi.getIdFollow());
        //bundle.putString("Email",followsDayApi.getEmail());
        fragment.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                replace(R.id.content_frame, fragment).addToBackStack(null);
        transaction.commit();
        controlGO(followsDayApi.getStart());
    }


    @Override
    public void tryAgainJsonFoods(String jsonFoods) {
        this.jsonFoods = jsonFoods;
    }

    @Override
    public void tryAgainJsonRecipes(String jsonRecipes) {
        this.jsonRecipes = jsonRecipes;
    }

    @Override
    public void tryAgainJsonFollows(String jsonFollows) {
        this.jsonFollows = jsonFollows;
    }

    @Override
    public void tryAgainJsonQuestions(String jsonQuestions) {
        this.jsonQuestions = jsonQuestions;
    }

    @Override
    public void tryAgainJsonLocations(String jsonLocations) {
        this.jsonLocations = jsonLocations;
    }

    @Override
    public void tryAgainJsonGuides(String jsonGuides) {
        this.jsonGuides = jsonGuides;
    }

    @Override
    public void tryAgainJsonUser(String jsonUser) {
        this.jsonUser=jsonUser;
        if (jsonUser != null) {
            userApi = gson.fromJson(jsonUser, UserApi.class);
            correo = userApi.getEmail();
            name = userApi.getName();
            genero = userApi.getGender();
            picture = userApi.getImage();
            dateOfBirthday=userApi.getDateOfBirth();
            weight=userApi.getWeight();
            height=userApi.getHeight();
            typeOfSolution=userApi.getTypeOfSolution();
            imc=userApi.getImc();
            typeOfTreatment=userApi.getTypeOfTreatment();
            dateCatheter=userApi.getDateCatheter();
            emergencyContact=userApi.getEmergencycontact();
            phoneEmergencySave(emergencyContact);
            setDatos();
        } else {
            tryAgainCargarTablaUser();
        }

    }

    @Override
    public void tryAgainJsonFollowsDay(String jsonFollowsDay) {
        this.jsonFollowsDay=jsonFollowsDay;
    }

    //Metodo para refescar el color de la app
    @Override
    public void refreshColor(int color) {
        if (this.color != color) {
            changeColors();
            changeToast(cambiosrealizados, ic_settings);
            toast.show();
        }
    }

    @Override
    public void closeFragment() {
        pila.popBackStack();
        controlBack();
    }


    @Override
    public void onBackPressed() {
        controlBack();
        super.onBackPressed();
    }

}
