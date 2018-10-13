package com.heroes.lesh.kidneys.Activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.Adapters.PageAdapterWelcome;
import com.heroes.lesh.kidneys.Animations.DepthPageTransformer;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.Follow;
import com.heroes.lesh.kidneys.Models.Food;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuideApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionApi;
import com.heroes.lesh.kidneys.Models.Recipe;
import com.heroes.lesh.kidneys.R;

import com.facebook.login.LoginManager;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WelcomeActivity extends AppCompatActivity implements ListenerPag, SeekBar.OnTouchListener, ViewPager.OnPageChangeListener {
    //variables globales
    @BindView(R.id.pagerWelcome)
    ViewPager viewPager;
    private PageAdapterWelcome pageAdapterWelcome;
    private Intent intent;
    @BindString(R.string.errorconexion)
    String errorconexion;
    @BindString(R.string.errorsesion)
    String errorsesion;
    @BindView(R.id.seekBarWelcome)
    SeekBar seekBarWelcome;
    @BindView(R.id.textViewNumPagesWelcome)
    TextView textViewNumPagesWelcome;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindString(R.string.pag)
    String pag;
    private Bundle bundleTablas;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private String correo;
    private SharedPreferences preferences;
    private String jsonFoods;
    private String jsonRecipes;
    private String jsonFollows;
    private String jsonQuestions;
    private String jsonLocations;
    private String jsonGuides;
    private String jsonUser;
    private String jsonFollowsDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //Poner la actividad en pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        Inicializar();
        correoPreferences();
        cargarTablas();
        pageAdapterWelcome = new PageAdapterWelcome(getSupportFragmentManager());
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setAdapter(pageAdapterWelcome);
        //Para que no sea usable por el usuario
        seekBarWelcome.setOnTouchListener(this);
        //Para saber la pagina actual
        viewPager.addOnPageChangeListener(this);
        numPageDefault();
    }

    //Metodo para inicializar componentes
    private void Inicializar() {
        inicializarToast();
        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

    }
    private void numPageDefault(){
        textViewNumPagesWelcome.setText(pag+"1/"+pageAdapterWelcome.getCount());
    }

    //Metodo para cargar las tablas, aun no lo usare hasta crear las tablas en el splash
    private void cargarTablas() {
        bundleTablas = getIntent().getExtras();
        if (bundleTablas != null) {//No llegara nulo por que es main a lo mejor o activity
            jsonFoods = bundleTablas.getString("Foods");
            jsonRecipes = bundleTablas.getString("Recipes");
            jsonFollows = bundleTablas.getString("Follows");
            jsonQuestions = bundleTablas.getString("Questions");
            jsonLocations = bundleTablas.getString("Locations");
            jsonGuides = bundleTablas.getString("Guides");
            jsonUser=bundleTablas.getString("User");
            jsonFollowsDay=bundleTablas.getString("FollowsDays");

            //  if(jsonFoods==null){
            //    jsonFoods="";
            //}
            //No necesitare aqui un array, solo el string y ese mandarlo al fragmento solo ejemplo para recuperar los demas
            //Borrar despues
            //foods=gson.fromJson(jsonFoods, new TypeToken<List<Food>>(){}.getType());
        }
    }

    //Metodo para inicializar el toast
    private void inicializarToast() {
        layoutInflater = getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    //Metodo para cambiar los valores del toast a mostrar
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para redigir a otra actividad
    private void cambiarActividad() {
        intent = new Intent(this, MainActivity.class);
        if (jsonFoods != null) {
            intent.putExtra("Foods", jsonFoods);
        }
        if (jsonRecipes != null) {
            intent.putExtra("Recipes", jsonRecipes);
        }
        if (jsonFollows != null) {
            intent.putExtra("Follows", jsonFollows);
        }
        if (jsonQuestions != null) {
            intent.putExtra("Questions", jsonQuestions);
        }
        if (jsonLocations != null) {
            intent.putExtra("Locations", jsonLocations);
        }
        if (jsonGuides != null) {
            intent.putExtra("Guides", jsonGuides);
        }
        if(jsonUser!=null){
            intent.putExtra("User",jsonUser);
        }
        if (jsonFollowsDay != null) {
            intent.putExtra("FollowsDays", jsonFollowsDay);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //Modificar animacion de transicion
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    //Metodo para recuperar el correo de las shared
    private void correoPreferences() {
        correo = preferences.getString("Email", "");
        if (TextUtils.equals(correo, "") || TextUtils.equals(correo, "no email")) {
            salir(errorsesion, ic_harmful);
        }
    }

    //Metodo para cambiar a la pantalla de inicio de sesion
    private void irLogin() {
        intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //Quitar animacion de transicion
        overridePendingTransition(0, 0);
    }

    //Metodo para cerrar sesion en facebook
    private void salir(String message, Drawable icon) {
        changeToast(message, icon);
        toast.show();
        //cierre de sesion
        LoginManager.getInstance().logOut();
        //Llamada al metodo de retorno a inicio de sesion
        irLogin();
    }


    //Metodo para cambiar el progreso del seekbar
    private void changeSeekBar(int position) {
        seekBarWelcome.setProgress(position);
    }

    //Metodo para cambiar el numero de pagina
    private void changeNumPage(int position){
        textViewNumPagesWelcome.setText(pag+" "+(position+1)+"/"+pageAdapterWelcome.getCount());
    }
    //Metodo llamado al destruir la actividad
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Metodo para sobreescribir el boton atras y asi poder regresar al fragment anterior
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            //Si se sale es redirigido diretamente al main
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void openFragment(int num) {

    }

    //Metodo para cerrar y cambiar de actividad
    @Override
    public void closeViewPagerAndOpenActivity() {
        cambiarActividad();
    }

    @Override
    public void sendRecipe(Recipe recipe) {

    }

    @Override
    public void sendFollow(Follow follow) {

    }

    @Override
    public void sendFood(Food food) {

    }

    @Override
    public void sendQuestion(QuestionApi questionApi) {

    }

    @Override
    public void sendGuide(GuideApi guideApi) {

    }

    @Override
    public void sendFollowDay(FollowsDayApi followsDayApi) {

    }


    @Override
    public void tryAgainJsonFoods(String jsonFoods) {

    }

    @Override
    public void tryAgainJsonRecipes(String jsonRecipes) {

    }

    @Override
    public void tryAgainJsonFollows(String jsonFollows) {

    }

    @Override
    public void tryAgainJsonQuestions(String jsonQuestions) {

    }

    @Override
    public void tryAgainJsonLocations(String jsonLocations) {

    }

    @Override
    public void tryAgainJsonGuides(String jsonGuides) {

    }

    @Override
    public void tryAgainJsonUser(String jsonUser) {

    }

    @Override
    public void tryAgainJsonFollowsDay(String jsonFollowsDay) {

    }


    @Override
    public void refreshColor(int color) {

    }

    @Override
    public void closeFragment() {

    }

    //Metodo para quitarle accion al usuario sobre el seekbar
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //Metodo para saber la pagina actual del viewpager
    @Override
    public void onPageSelected(int position) {
        changeSeekBar(position);
        changeNumPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
