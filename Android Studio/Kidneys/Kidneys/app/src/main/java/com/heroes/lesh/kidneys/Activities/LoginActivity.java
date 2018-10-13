package com.heroes.lesh.kidneys.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.CancellationSignal;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.FragmentsRegister.RegisterFragment;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.Follow;
import com.heroes.lesh.kidneys.Models.Food;
import com.heroes.lesh.kidneys.Models.ModelsApi.FollowsDayApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.GuideApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.QuestionApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserCreateApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.ValidateUserApi;
import com.heroes.lesh.kidneys.Models.Recipe;
import com.heroes.lesh.kidneys.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindFont;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements ListenerPag {
    //Variables globales
    private Intent intent;
    @BindView(R.id.login_button)
    LoginButton loginButton;
    @BindView(R.id.sign_in_button)
    SignInButton sign_in_button;
    @BindView(R.id.textViewLogin)
    TextView textViewLogin;
    @BindView(R.id.textViewLoginNo)
    TextView textViewLoginNo;
    @BindView(R.id.layoutLogin)
    LinearLayout layoutLogin;
    @BindView(R.id.buttonLogin)
    Button buttonLogin;
    @BindView(R.id.editTextLoginEmail)
    EditText editTextLoginEmail;
    @BindView(R.id.editTextLoginPassword)
    EditText editTextLoginPassword;
    @BindView(R.id.checkBoxRememberEmailLogin)
    CheckBox checkBoxRememberEmailLogin;
    @BindView(R.id.checkBoxRememberPasswordLogin)
    CheckBox checkBoxRememberPasswordLogin;
    @BindString(R.string.cancelado)
    String cancelado;
    @BindString(R.string.errorconexion)
    String errorconexion;
    @BindString(R.string.errorsesion)
    String errorsesion;
    @BindString(R.string.errordatos)
    String errordatos;
    @BindString(R.string.registro)
    String registro;
    @BindString(R.string.noregistro)
    String noregistro;
    @BindString(R.string.bienvenidootra)
    String bienvenidootra;
    @BindString(R.string.iniciarnormal)
    String iniciarnormal;
    @BindString(R.string.errorraro)
    String errorraro;
    @BindString(R.string.correoinvalido)
    String correoinvalido;
    @BindString(R.string.contrainvalido)
    String contrainvalido;
    @BindString(R.string.inicioincorrecto)
    String inicioincorrecto;
    @BindString(R.string.adverrecordar)
    String adverrecordar;
    @BindString(R.string.iniciando)
    String iniciando;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.mipmap.ic_kidneys)
    Drawable ic_kidneys;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    @BindColor(R.color.colorRojo)
    int colorRojo;
    @BindColor(R.color.colorNegro)
    int colorNegro;
    @BindFont(R.font.alice)
    Typeface alice;
    private Bundle bundleTablas;
    private CallbackManager callbackManager;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private SharedPreferences preferences;
    private String jsonFoods;
    private String jsonRecipes;
    private String jsonFollows;
    private String jsonQuestions;
    private String jsonLocations;
    private String jsonGuides;
    private String jsonUser;
    private String jsonFollowsDay;
    private Fragment fragment;
    private FragmentTransaction transaction;
    private String correo;
    private String password;
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
    private KidneysService kidneysService;
    private UserCreateApi userCreateApi;
    private ValidateUserApi validateUserApi;
    private int watchWelcome;
    private FragmentManager pila;
    private final int RC_SIGN_IN = 1001;
    private Task<GoogleSignInAccount> task;
    private URL url;
    private Intent signInIntent;
    String rememberEmail;
    String rememberPassword;
    private SharedPreferences.Editor editor;
   private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Evitar que el teclado se abra al iniciar la actividad o el fragmento
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //Poner la actividad en pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        Inicializar();
        //Inicializar el toast
        inicializarToast();
        //Recuerda los datos
        remember();
        //Cambia la fuente del boton de facebook
        changeFont();
        //Cambiar colores de preferencia
        changeColors();
        //Cargar las tablas
        cargarTablas();
        //Permisos de google
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        //Objeto de google
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //Permisos de facebook
        mGoogleSignInClient.signOut();
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        //Callback de la interfaz de facebook
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            //Metodo de inicio de sesion correcto
            public void onSuccess(LoginResult loginResult) {
                requerirDatos(AccessToken.getCurrentAccessToken());
            }

            @Override
            //Metodo para la cancelacion del inicio de sesion
            public void onCancel() {
                changeToast(cancelado, ic_harmful);
                toast.show();
            }

            @Override
            //Metodo para validar errores del inicio de sesion
            public void onError(FacebookException errorr) {
                // Toast.makeText(getApplicationContext(), errorconexion, Toast.LENGTH_SHORT).show();
                changeToast(errorconexion, ic_harmful);
                toast.show();
            }
        });
        sign_in_button.setSize(sign_in_button.SIZE_WIDE);
        sign_in_button.setColorScheme(sign_in_button.COLOR_LIGHT);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


        textViewLoginNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutLogin.setVisibility(View.GONE);
                openFragment();
            }
        });

        editTextLoginEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validateEmail(editTextLoginEmail.getText().toString()) && !TextUtils.equals(editTextLoginEmail.getText().toString(), "")) {
                        changeToast(correoinvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextLoginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateEmail(s.toString())) {
                    editTextLoginEmail.setTextColor(colorNegro);
                    editTextLoginEmail.setHintTextColor(colorNegro);
                } else {
                    editTextLoginEmail.setTextColor(colorRojo);
                    editTextLoginEmail.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextLoginPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validatePassword(editTextLoginPassword.getText().toString()) && !TextUtils.equals(editTextLoginPassword.getText().toString(), "")) {
                        changeToast(contrainvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePassword(s.toString())) {
                    editTextLoginPassword.setTextColor(colorNegro);
                    editTextLoginPassword.setHintTextColor(colorNegro);
                } else {
                    editTextLoginPassword.setTextColor(colorRojo);
                    editTextLoginPassword.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        checkBoxRememberEmailLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    checkBoxRememberPasswordLogin.setChecked(false);
                }
            }
        });
        checkBoxRememberPasswordLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!checkBoxRememberEmailLogin.isChecked()) {
                        checkBoxRememberPasswordLogin.setChecked(false);
                        changeToast(adverrecordar, ic_kidneys);
                        toast.show();
                    }
                }
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmail(editTextLoginEmail.getText().toString())) {
                    if (validatePassword(editTextLoginPassword.getText().toString())) {
                        correo = editTextLoginEmail.getText().toString().trim();
                        password = editTextLoginPassword.getText().toString();
                        try {
                            password=toSHA1(password.getBytes("UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        changeProgressDialog(iniciando);
                        progressDialog.show();
                        validateUser(correo, password);
                        registerDataLogin();
                    } else {
                        changeToast(contrainvalido, ic_harmful);
                        toast.show();
                    }
                } else {
                    changeToast(correoinvalido, ic_harmful);
                    toast.show();
                }
            }
        });

        buttonLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(iniciarnormal, ic_kidneys);
                toast.show();
                return false;
            }
        });

    }
//Metodo para encriptar
    public static String toSHA1(byte[] convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteArrayToHexString(md.digest(convertme));
    }
//Metodo para convertir de hex
    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
                    Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
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

    //Metodo para validar email
    private boolean validateEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    //Metodo para validar password
    private boolean validatePassword(String password) {
        return (password.length() > 5);
    }

    //Metodo para inicializar componentes
    private void Inicializar() {
        callbackManager = CallbackManager.Factory.create();
        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        watchWelcome = preferences.getInt("WatchWelcome", 0);
        kidneysService = Api.getApi().create(KidneysService.class);
        pila = getSupportFragmentManager();
        progressDialog = new ProgressDialog(this);
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

    private void changeProgressDialog(String message){
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    //Metodo para cambiar el el toast
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
        if (jsonUser != null) {
            intent.putExtra("User", jsonUser);
        }
        if (jsonFollowsDay != null) {
            intent.putExtra("FollowsDays", jsonFollowsDay);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //Editar animacion de transicion
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    //Metodo para solicitar los datos al usuario y validar que lo acepte
    private void requerirDatos(AccessToken currentAccessToken) {
        if (currentAccessToken != null && !currentAccessToken.isExpired()) {
            GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    if (response.getError() != null) {
                        salir(errorconexion, ic_harmful);
                        return;
                    }
                    try {
                        //Toast.makeText(getApplicationContext(),object.getString("email"), Toast.LENGTH_SHORT).show();
                        correo = object.getString("email");
                        name = object.getString("first_name") + " " + object.getString("last_name");
                        try {
                            url = new URL("https://graph.facebook.com/" + object.getString("id") + "/picture?width=500&height=500");
                            picture = url.toString();
                            //Insertar en la bd mediante HTTP request
                            registerUser(correo, password, name, picture);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            changeToast(errordatos, ic_harmful);
                            toast.show();
                        }
                    } catch (JSONException e) {
                        changeToast(errordatos, ic_harmful);
                        toast.show();
                    }
                }
            });
            //Parametros a enviar en la actividad
            Bundle parameters = new Bundle();
            parameters.putString("fields", "email,first_name,last_name");
            request.setParameters(parameters);
            request.executeAsync();
        } else {
            // Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();//Error de email
            salir(errorsesion, ic_harmful);
        }
    }

    //Metodo para insertar un nuevo usuario en la bd
    private void registerUser(final String correo, String password, String name,  String picture) {
        kidneysService.createUserApi(correo, password, name, genero, picture,dateOfBirthday,weight,height,dateCatheter,
                typeOfSolution,imc,typeOfTreatment,emergencyContact).enqueue(new retrofit2.Callback<UserCreateApi>() {
            @Override
            public void onResponse(Call<UserCreateApi> call, Response<UserCreateApi> response) {
                userCreateApi = response.body();

                if (TextUtils.equals(userCreateApi.getStatus(), "true")) {
                    registrarEmailPreferences(correo);
                    changeToast(registro, ic_healthy);
                    toast.show();
                    if (watchWelcome()) {
                        cambiarActividad();
                    }
                } else if (TextUtils.equals(userCreateApi.getStatus(), "false")) {
                    salir(noregistro, ic_harmful);//api no terminada
                } else if (TextUtils.equals(userCreateApi.getStatus(), "already")) {
                    registrarEmailPreferences(correo);
                    if (watchWelcome()) {
                        changeToast(bienvenidootra, ic_healthy);
                        toast.show();
                        cambiarActividad();
                    }
                } else {
                    salir(errorraro, ic_harmful);//api no terminada
                }
            }

            @Override
            public void onFailure(Call<UserCreateApi> call, Throwable t) {
                salir(errorconexion, ic_harmful);
            }
        });
    }

    //Metodo para validar el usuario en la api
    private void validateUser(final String correo, String password) {
        kidneysService.validateUserApi(correo, password).enqueue(new retrofit2.Callback<ValidateUserApi>() {
            @Override
            public void onResponse(Call<ValidateUserApi> call, Response<ValidateUserApi> response) {
                progressDialog.dismiss();
                validateUserApi = response.body();
                if (TextUtils.equals(validateUserApi.getStatus(), "true")) {
                    registrarEmailPreferences(correo);
                    changeToast(bienvenidootra, ic_healthy);
                    toast.show();
                    if (watchWelcome()) {
                        cambiarActividad();
                    }
                } else if (TextUtils.equals(validateUserApi.getStatus(), "false")) {
                    changeToast(inicioincorrecto, ic_harmful);
                    toast.show();
                } else {
                    changeToast(errorraro, ic_harmful);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ValidateUserApi> call, Throwable t) {
                progressDialog.dismiss();
                changeToast(errorconexion, ic_harmful);
                toast.show();
            }
        });
    }

    //Metodo para verificar si vio el welcome
    private boolean watchWelcome() {
        if (watchWelcome == 0 || watchWelcome == 1) {
            irWelcome();
            return false;
        } else {
            return true;
        }
    }

    //Metodo para cambiar a la pantalla de welcome
    private void irWelcome() {
        intent = new Intent(this, WelcomeActivity.class);
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
        if (jsonUser != null) {
            intent.putExtra("User", jsonUser);
        }
        if (jsonFollowsDay != null) {
            intent.putExtra("FollowsDays", jsonFollowsDay);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //Quitar animacion de transicion
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    //Metodo para recuperar el color requerido
    private void changeColors() {
        switch (preferences.getInt("ColorApp", 1)) {
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
        textViewLogin.setBackgroundColor(color);
        buttonLogin.setBackgroundColor(color);
    }

    //Metodo para registrar el email en las preferencias
    private void registrarEmailPreferences(String email) {
        editor.putString("Email", email);
        editor.apply();
    }

    private void registerDataLogin(){
        if(checkBoxRememberEmailLogin.isChecked()) {
            rememberEmail=editTextLoginEmail.getText().toString();
            editor.putString("RememberEmail", rememberEmail);
            if(checkBoxRememberPasswordLogin.isChecked()) {
                rememberPassword=editTextLoginPassword.getText().toString();
                editor.putString("RememberPassword", rememberPassword);
            }else{
                editor.putString("RememberPassword", "");
            }
            editor.apply();
        }else{
            editor.putString("RememberEmail", "");
            editor.putString("RememberPassword", "");
            editor.apply();
        }
    }

    private void remember() {
        rememberEmail = preferences.getString("RememberEmail", "");
        rememberPassword = preferences.getString("RememberPassword", "");
        if (!TextUtils.equals(rememberEmail, "")) {
            editTextLoginEmail.setText(rememberEmail);
            checkBoxRememberEmailLogin.setChecked(true);
            if (!TextUtils.equals(rememberPassword, "")) {
                editTextLoginPassword.setText(rememberPassword);
                checkBoxRememberPasswordLogin.setChecked(true);
            }
        }
    }

    //Metodo para cerrar sesion en facebook
    private void salir(String message, Drawable icon) {
        changeToast(message, icon);
        toast.show();
        //cierre de sesion
        LoginManager.getInstance().logOut();
        mGoogleSignInClient.signOut();
    }

    //Metodo para abrir fragmento de registro
    public void openFragment() {
        fragment = new RegisterFragment();
        transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter, R.anim.exit, 0, 0).
                replace(R.id.content_register, fragment).addToBackStack(null);
        transaction.commit();
    }

    private void changeFont() {
       // loginButton.setTypeface(alice);
        checkBoxRememberEmailLogin.setTypeface(alice);
        checkBoxRememberPasswordLogin.setTypeface(alice);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            correo = account.getEmail();
            name = account.getDisplayName();
            if(account.getPhotoUrl()!=null) {
                picture = account.getPhotoUrl().toString();
            }else{
                picture="Sin imagen";
            }
            registerUser(correo, password, name, picture);
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            salir(errorconexion, ic_harmful);
        }
    }

    //Sobreescritura del metodo del resultado de la actividad
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        layoutLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void openFragment(int num) {

    }


    @Override
    public void closeViewPagerAndOpenActivity() {

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

    //Metodo para cerrar fragmento desde fragment con callback
    @Override
    public void closeFragment() {
        pila.popBackStack();
        layoutLogin.setVisibility(View.VISIBLE);

    }
}
