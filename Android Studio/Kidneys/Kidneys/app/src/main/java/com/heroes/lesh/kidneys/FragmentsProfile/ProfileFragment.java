package com.heroes.lesh.kidneys.FragmentsProfile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserApi;
import com.heroes.lesh.kidneys.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private ListenerPag callback;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.txtProfileName)
    TextView txtProfileName;
    @BindView(R.id.txtProfileCorreo)
    TextView txtProfileCorreo;
    @BindView(R.id.txtProfileGender)
    TextView txtProfileGender;
    @BindView(R.id.card_viewProfileTop)
    CardView card_viewProfileTop;
    @BindView(R.id.txtProfileAge)
    TextView txtProfileAge;
    @BindView(R.id.txtProfileWeight)
    TextView txtProfileWeight;
    @BindView(R.id.txtProfileHeight)
    TextView txtProfileHeight;
    @BindView(R.id.txtProfileDateCatheter)
    TextView txtProfileDateCatheter;
    @BindView(R.id.txtProfileTypeOfSolution)
    TextView txtProfileTypeOfSolution;
    @BindView(R.id.txtProfileImc)
    TextView txtProfileImc;
    @BindView(R.id.txtProfileTypeOfTreatment)
    TextView txtProfileTypeOfTreatment;
    @BindView(R.id.txtProfileEmergencyContact)
    TextView txtProfileEmergencyContact;
    @BindView(R.id.floatingPasswordProfile)
    FloatingActionButton floatingPasswordProfile;
    @BindView(R.id.floatingEditProfile)
    FloatingActionButton floatingEditProfile;
    @BindView(R.id.card_view_profile_imc)
    CardView card_view_profile_imc;
    @BindDrawable(R.drawable.loading)
    Drawable loading;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.mipmap.ic_profile)
    Drawable ic_profile;
    @BindString(R.string.reintentardatos)
    String reintentardatos;
    @BindString(R.string.cargacorrecta)
    String cargacorrecta;
    @BindString(R.string.errorcarga)
    String errorcarga;
    @BindString(R.string.masculino)
    String masculino;
    @BindString(R.string.femenino)
    String femenino;
    @BindString(R.string.anios)
    String anios;
    @BindString(R.string.kilogramos)
    String kilogramos;
    @BindString(R.string.metros)
    String metros;
    @BindString(R.string.cambiarcontra)
    String cambiarcontra;
    @BindString(R.string.actualizarperfil)
    String actualizarperfil;
    @BindString(R.string.genero)
    String generostring;
    @BindString(R.string.edad)
    String edad;
    @BindString(R.string.peso)
    String peso;
    @BindString(R.string.altura)
    String altura;
    @BindString(R.string.fechacateter)
    String fechacateter;
    @BindString(R.string.tipodesolucion)
    String tipodesolucion;
    @BindString(R.string.imc)
    String imcString;
    @BindString(R.string.tipodetratamiento)
    String tipodetratamiento;
    @BindString(R.string.contactoemergencia)
    String contactoemergencia;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    @BindColor(R.color.colorVerde)
    int colorVerde;
    @BindColor(R.color.colorRojo)
    int colorRojo;
    private SharedPreferences preferences;
    private Unbinder unbinder;
    private View view;
    private Bundle bundle;
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
    private UserApi userApi;
    private KidneysService kidneysService;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private String jsonUser;
    private Gson gson;
    private SimpleDateFormat dateFormat;
    private Calendar dateBirthday;
    private Calendar today;
    private int diffYear;
    private int diffMonth;
    private int diffDay;
    private boolean updateProfile;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //Inicializacion del callback de la interfaz
            callback = (ListenerPag) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        updateProfile=false;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        changeColors();
        correoPreferences();
        bundle = getArguments();
        if (bundle != null&&!updateProfile) {
            correo = bundle.getString("Correo");
            name = bundle.getString("Name");
            picture = bundle.getString("Picture");
            genero = bundle.getString("Genero");
            dateOfBirthday = bundle.getString("DateOfBirthday");
            weight = bundle.getDouble("Weight");
            height = bundle.getDouble("Height");
            dateCatheter = bundle.getString("DateCatheter");
            typeOfSolution=bundle.getDouble("TypeOfSolution");
            imc=bundle.getDouble("Imc");
            typeOfTreatment=bundle.getString("TypeOfTreatment");
            emergencyContact=bundle.getString("EmergencyContact");
            showEdits();
            changeData(correo, name, picture, genero, dateOfBirthday, weight, height, dateCatheter,typeOfSolution,imc,typeOfTreatment,emergencyContact);
        } else {
            changeToast(reintentardatos, ic_profile);
            toast.show();
            hideEdits();
            tryAgainCargarTablaUser();
        }
        floatingPasswordProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment(12);
            }
        });
        floatingPasswordProfile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(cambiarcontra,ic_profile);
                toast.show();
                return false;
            }
        });
        floatingEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment(13);
            }
        });
        floatingEditProfile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(actualizarperfil,ic_profile);
                toast.show();
                return false;
            }
        });
        return view;
    }

    //Metodo para recuperar el correo de las shared
    private void correoPreferences() {
        correo = preferences.getString("Email", "");
        if (TextUtils.equals(correo, "") || TextUtils.equals(correo, "no email")) {
            // salir(errorsesion, ic_harmful);
        }
    }

    //Metodo para mostrar botones
    public void showEdits(){
        floatingPasswordProfile.setVisibility(View.VISIBLE);
        floatingEditProfile.setVisibility(View.VISIBLE);
    }
    //Metodo para ocultar botones
    public void hideEdits(){
        floatingPasswordProfile.setVisibility(View.GONE);
        floatingEditProfile.setVisibility(View.GONE);
    }
    private void tryAgainCargarTablaUser() {
        selectUser(correo);
    }

    //Metodo para recuperar el usuario de la api por si quita permisos en facebook
    private void selectUser(String correoApi) {
        kidneysService.selectUserApi(correoApi).enqueue(new retrofit2.Callback<UserApi>() {
            @Override
            public void onResponse(Call<UserApi> call, Response<UserApi> response) {
                userApi = response.body();
                if (userApi == null) {

                } else {
                    correo = userApi.getEmail();
                    name = userApi.getName();
                    genero = userApi.getGender();
                    picture = userApi.getImage();
                    dateOfBirthday = userApi.getDateOfBirth();
                    weight = userApi.getWeight();
                    height = userApi.getHeight();
                    dateCatheter = userApi.getDateCatheter();
                    typeOfSolution=userApi.getTypeOfSolution();
                    imc=userApi.getImc();
                    typeOfTreatment=userApi.getTypeOfTreatment();
                    emergencyContact=userApi.getEmergencycontact();
                    showEdits();
                    changeData(correo, name, picture, genero, dateOfBirthday, weight, height, dateCatheter,typeOfSolution,imc,typeOfTreatment,emergencyContact);
                    changeToast(cargacorrecta, ic_healthy);
                    toast.show();
                    jsonUser = gson.toJson(userApi);
                    sendJsonUser(jsonUser);
                }
            }

            @Override
            public void onFailure(Call<UserApi> call, Throwable t) {
                hideEdits();
                changeToast(errorcarga, ic_harmful);
                toast.show();
            }
        });
    }
    private void sendFragment(int num){
        callback.openFragment(num);
    }

    //Metodo para reenviar el jsonuser
    private void sendJsonUser(String jsonUser) {
        callback.tryAgainJsonUser(jsonUser);
    }

    //Metodo para inicializar el toadt
    private void inicializarToast() {
        layoutInflater = getActivity().getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    //Metodo para cambiar el toast
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        kidneysService = Api.getApi().create(KidneysService.class);
        gson = new Gson();
    }

    //Metodo para cambiar los datos del usuario
    public void changeData(String correo, String name, String picture, String genero, String dateOfBirthday,
                           double weight, double height, String dateCatheter,double typeOfSolution,double imc,String typeOfTreatment,String emergencyContact) {
        //este if es por si falla el target por el unbinder en crearse
            Picasso.get().load(picture).placeholder(loading).into(imgProfile, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(R.drawable.profile).placeholder(loading).into(imgProfile);
                }
            });
        txtProfileName.setText(name);
        txtProfileCorreo.setText(correo);
        if (TextUtils.equals(genero, "M")) {
            genero = masculino;
        }
        if (TextUtils.equals(genero, "F")) {
            genero = femenino;
        }
        txtProfileGender.setText(generostring+": " + genero);
        txtProfileAge.setText(edad+"\n"+Age(dateOfBirthday)+" "+anios);
        txtProfileWeight.setText(peso+"\n" + weight + " " + kilogramos);
        txtProfileHeight.setText(altura+"\n" + height + " " + metros);
        if(TextUtils.equals(dateCatheter,"0000-00-00")){
            dateCatheter="";
        }
        txtProfileDateCatheter.setText(fechacateter+": " + dateCatheter);
        txtProfileTypeOfSolution.setText(tipodesolucion+": "+typeOfSolution);
        txtProfileImc.setText(imcString+"\n"+imc);
        changeColorImc(imc);
        txtProfileTypeOfTreatment.setText(tipodetratamiento+": "+typeOfTreatment);
        txtProfileEmergencyContact.setText(contactoemergencia+": "+emergencyContact);
    }

    //Metodo para cambiar imc de color
    private void changeColorImc(double imc){
        if(imc<=18.4){
            card_view_profile_imc.setCardBackgroundColor(colorRojo);
        }else if(imc>=18.5&&imc<=24.9){
            card_view_profile_imc.setCardBackgroundColor(colorVerde);
        }else if(imc>=25&&imc<=29.9){
            card_view_profile_imc.setCardBackgroundColor(colorMarron);
        }else{
            card_view_profile_imc.setCardBackgroundColor(colorRojo);
        }
    }

    //Metodo para calcular edad
    private int Age(String dateOfBirthday) {
        if(!TextUtils.equals(dateCatheter,"0000-00-00")){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateBirthday = Calendar.getInstance();
        today = Calendar.getInstance();
        try {
            dateBirthday.setTime(dateFormat.parse(dateOfBirthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        diffYear = today.get(Calendar.YEAR) - dateBirthday.get(Calendar.YEAR);
        diffMonth = today.get(Calendar.MONTH) - dateBirthday.get(Calendar.MONTH);
        diffDay = today.get(Calendar.DAY_OF_MONTH) - dateBirthday.get(Calendar.DAY_OF_MONTH);
        // Si está en ese año pero todavía no los ha cumplido
        if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear - 1;
        }
        return diffYear;
        }else{
            return 0;
        }

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
        card_viewProfileTop.setCardBackgroundColor(color);
    }

    @Override
    public void onResume() {
        updateProfile=true;
        super.onResume();
    }

    //Metodo llamado al desturir el fragment
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
