package com.heroes.lesh.kidneys.FragmentsRegister;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.FragmentsFollow.FragmentsFollowFollow.FollowFollowsFragment;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserCreateApi;
import com.heroes.lesh.kidneys.R;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class RegisterFragment extends Fragment {
    //Variables globales
    private View view;
    private Unbinder unbinder;
    @BindView(R.id.editTextNameRegister)
    EditText editTextNameRegister;
    @BindView(R.id.editTextEmailRegister)
    EditText editTextEmailRegister;
    @BindView(R.id.editTextPasswordRegister)
    EditText editTextPasswordRegister;
    @BindView(R.id.editTextPasswordConfirmRegister)
    EditText editTextPasswordConfirmRegister;
    @BindView(R.id.spinnerRegister)
    Spinner spinnerRegister;
    @BindView(R.id.editTextDateOfBirthRegister)
    EditText editTextDateOfBirthRegister;
    @BindView(R.id.editTextWeightRegister)
    EditText editTextWeightRegister;
    @BindView(R.id.editTextHeightRegister)
    EditText editTextHeightRegister;
    @BindView(R.id.editTextDateCatheterRegister)
    EditText editTextDateCatheterRegister;
    @BindView(R.id.editTextContactEmergencyRegister)
    EditText editTextContactEmergencyRegister;
    @BindView(R.id.spinnerRegisterTypeOfSolution)
    Spinner spinnerRegisterTypeOfSolution;
    @BindView(R.id.spinnerRegisterTypeOfTreatment)
    Spinner spinnerRegisterTypeOfTreatment;
    @BindView(R.id.spinnerRegisterExtension)
    Spinner spinnerRegisterExtension;
    @BindView(R.id.buttonRegister)
    Button buttonRegister;
    @BindString(R.string.masculino)
    String masculino;
    @BindString(R.string.femenino)
    String femenino;
    @BindString(R.string.nombreinvalido)
    String nombreinvalido;
    @BindString(R.string.correoinvalido)
    String correoinvalido;
    @BindString(R.string.contrainvalido)
    String contrainvalido;
    @BindString(R.string.contranocoincide)
    String contranocoincide;
    @BindString(R.string.registro)
    String registro;
    @BindString(R.string.noregistro)
    String noregistro;
    @BindString(R.string.correonodisponible)
    String correonodisponible;
    @BindString(R.string.errorraro)
    String errorraro;
    @BindString(R.string.errorconexion)
    String errorconexion;
    @BindString(R.string.registrarme)
    String registrarme;
    @BindString(R.string.pesoinvalido)
    String pesoinvalido;
    @BindString(R.string.alturainvalida)
    String alturainvalida;
    @BindString(R.string.fechanovalida)
    String fechanovalida;
    @BindString(R.string.ambulatoria)
    String ambulatoria;
    @BindString(R.string.ciclica)
    String ciclica;
    @BindString(R.string.telefonoinvalido)
    String telefonoinvalido;
    @BindString(R.string.registrando)
    String registrando;
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
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.mipmap.ic_kidneys)
    Drawable ic_kidneys;
    private ArrayList<String> genders;
    private ArrayList<String> typesofsolution;
    ArrayList<String> typesoftreatment;
    ArrayList<String> extensions;
    private SharedPreferences preferences;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private KidneysService kidneysService;
    private UserCreateApi userCreateApi;
    private String correo;
    private String password;
    private String name;
    private String genero;
    private String picture;
    private String dateOfBirthday;
    private double typeOfSolution;
    private double imc;
    private String typeOfTreatment;
    private double weight;
    private double height;
    private String dateCatheter;
    private String emergencyContact;
    private ListenerPag callback;
    private SimpleDateFormat dateFormat;
    private Date date;
    private String dateInString;
    private Date dateNow;
    private Date dateSelect;
    private Date dateBirth;
    private ProgressDialog progressDialog;


    public RegisterFragment() {
        // Required empty public constructor
    }

    //Metodo para iniciar el listener de la interfaz
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        //Cambiar colores
        changeColors();
        spinnerRegister.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, genders));
        spinnerRegister.getSelectedItem().toString();
        spinnerRegisterTypeOfSolution.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, typesofsolution));
        spinnerRegisterTypeOfSolution.getSelectedItem().toString();
        spinnerRegisterTypeOfTreatment.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, typesoftreatment));
        spinnerRegisterTypeOfTreatment.getSelectedItem().toString();
        spinnerRegisterExtension.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, extensions));
        spinnerRegisterExtension.getSelectedItem().toString();

        editTextNameRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validateName(editTextNameRegister.getText().toString()) && !TextUtils.equals(editTextNameRegister.getText().toString(), "")) {
                        changeToast(nombreinvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextNameRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateName(s.toString())) {
                    editTextNameRegister.setTextColor(colorNegro);
                    editTextNameRegister.setHintTextColor(colorNegro);
                } else {
                    editTextNameRegister.setTextColor(colorRojo);
                    editTextNameRegister.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextEmailRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validateEmail(editTextEmailRegister.getText().toString()) && !TextUtils.equals(editTextEmailRegister.getText().toString(), "")) {
                        changeToast(correoinvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextEmailRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateEmail(s.toString())) {
                    editTextEmailRegister.setTextColor(colorNegro);
                    editTextEmailRegister.setHintTextColor(colorNegro);
                } else {
                    editTextEmailRegister.setTextColor(colorRojo);
                    editTextEmailRegister.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextPasswordRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validatePassword(editTextPasswordRegister.getText().toString()) && !TextUtils.equals(editTextPasswordRegister.getText().toString(), "")) {
                        changeToast(contrainvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextPasswordRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePassword(s.toString())) {
                    editTextPasswordRegister.setTextColor(colorNegro);
                    editTextPasswordRegister.setHintTextColor(colorNegro);
                } else {
                    editTextPasswordRegister.setTextColor(colorRojo);
                    editTextPasswordRegister.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextPasswordConfirmRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validatePasswordConfirm(editTextPasswordConfirmRegister.getText().toString()) && !TextUtils.equals(editTextPasswordConfirmRegister.getText().toString(), "")) {
                        changeToast(contranocoincide, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextPasswordConfirmRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePasswordConfirm(s.toString())) {
                    editTextPasswordConfirmRegister.setTextColor(colorNegro);
                    editTextPasswordConfirmRegister.setHintTextColor(colorNegro);
                } else {
                    editTextPasswordConfirmRegister.setTextColor(colorRojo);
                    editTextPasswordConfirmRegister.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextDateOfBirthRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogBirthday();
            }
        });
        editTextDateOfBirthRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        editTextWeightRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!validateWeight(editTextWeightRegister.getText().toString())&&!TextUtils.equals(editTextWeightRegister.getText().toString(),"")){
                        changeToast(pesoinvalido,ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextWeightRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateWeight(s.toString())) {
                    editTextWeightRegister.setTextColor(colorNegro);
                    editTextWeightRegister.setHintTextColor(colorNegro);
                } else {
                    editTextWeightRegister.setTextColor(colorRojo);
                    editTextWeightRegister.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextHeightRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!validateHeight(editTextHeightRegister.getText().toString())&&!TextUtils.equals(editTextHeightRegister.getText().toString(),"")){
                        changeToast(alturainvalida,ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextHeightRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateHeight(s.toString())) {
                    editTextHeightRegister.setTextColor(colorNegro);
                    editTextHeightRegister.setHintTextColor(colorNegro);
                } else {
                    editTextHeightRegister.setTextColor(colorRojo);
                    editTextHeightRegister.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextDateCatheterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogCatheter();
            }
        });
        editTextDateCatheterRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        editTextContactEmergencyRegister.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!validatePhone(editTextContactEmergencyRegister.getText().toString())&&!TextUtils.equals(editTextContactEmergencyRegister.getText().toString(),"")){
                        changeToast(telefonoinvalido,ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextContactEmergencyRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePhone(s.toString())) {
                    editTextContactEmergencyRegister.setTextColor(colorNegro);
                    editTextContactEmergencyRegister.setHintTextColor(colorNegro);
                } else {
                    editTextContactEmergencyRegister.setTextColor(colorRojo);
                    editTextContactEmergencyRegister.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateName(editTextNameRegister.getText().toString())) {
                    if (validateEmail(editTextEmailRegister.getText().toString())) {
                        if (validatePassword(editTextPasswordRegister.getText().toString())) {
                            if (validatePasswordConfirm(editTextPasswordConfirmRegister.getText().toString())) {
                                if(validateWeight(editTextWeightRegister.getText().toString())){
                                    if(validateHeight(editTextHeightRegister.getText().toString())){
                                        if(validatePhone(editTextContactEmergencyRegister.getText().toString())) {
                                            if (TextUtils.equals(spinnerRegister.getSelectedItem().toString(), masculino)) {
                                                genero = "M";
                                            } else {
                                                genero = "F";
                                            }
                                            correo = editTextEmailRegister.getText().toString().trim();
                                            password = editTextPasswordConfirmRegister.getText().toString();
                                            name = editTextNameRegister.getText().toString().trim();
                                            picture = "Sin imagen";
                                            try {
                                                password = toSHA1(password.getBytes("UTF-8"));
                                            } catch (UnsupportedEncodingException e) {
                                                e.printStackTrace();
                                            }
                                            dateOfBirthday = editTextDateOfBirthRegister.getText().toString().trim();
                                            weight = Double.parseDouble(editTextWeightRegister.getText().toString().trim());
                                            height = Double.parseDouble(editTextHeightRegister.getText().toString().trim());
                                            dateCatheter = editTextDateCatheterRegister.getText().toString().trim();
                                            typeOfSolution = Double.parseDouble(spinnerRegisterTypeOfSolution.getSelectedItem().toString());
                                            typeOfTreatment = spinnerRegisterTypeOfTreatment.getSelectedItem().toString();
                                            imc = calculateImc(height, weight);
                                            emergencyContact=phoneEmergency(spinnerRegisterExtension.getSelectedItem().toString(),
                                                    editTextContactEmergencyRegister.getText().toString()).trim();
                                            changeProgressDialog(registrando);
                                            progressDialog.show();
                                            registerUser(correo, password, name, genero, picture, dateOfBirthday, weight, height, dateCatheter,
                                                    typeOfSolution, imc, typeOfTreatment,emergencyContact);
                                        }else{
                                            changeToast(telefonoinvalido,ic_harmful);
                                            toast.show();
                                        }
                                    }else{
                                        changeToast(alturainvalida,ic_harmful);
                                        toast.show();
                                    }
                                }else{
                                    changeToast(pesoinvalido,ic_harmful);
                                    toast.show();
                                }
                            } else {
                                changeToast(contranocoincide, ic_harmful);
                                toast.show();
                            }
                        } else {
                            changeToast(contrainvalido, ic_harmful);
                            toast.show();
                        }
                    } else {
                        changeToast(correoinvalido, ic_harmful);
                        toast.show();
                    }

                } else {
                    changeToast(nombreinvalido, ic_harmful);
                    toast.show();
                }
            }
        });
        buttonRegister.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(registrarme, ic_kidneys);
                toast.show();
                return false;
            }
        });
        return view;
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

    private String phoneEmergency(String extension,String phone){
        return extension+phone;
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        genders = gender();
        typesofsolution=typeofsolution();
        typesoftreatment=typeoftreatment();
        extensions=extension();
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        kidneysService = Api.getApi().create(KidneysService.class);
        editTextDateOfBirthRegister.setInputType(InputType.TYPE_NULL);
        editTextDateCatheterRegister.setInputType(InputType.TYPE_NULL);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date = new Date();
        dateInString = dateFormat.format(date);
        try {
            dateNow=dateFormat.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(dateInString)) {
            editTextDateOfBirthRegister.setText(dateInString);
            editTextDateCatheterRegister.setText(dateInString);
        }
        dateBirth=dateNow;
        progressDialog = new ProgressDialog(getActivity());
    }

    private boolean validateName(String name) {
        return (name.length() > 5);
    }

    private boolean validateEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean validatePassword(String password) {
        return (password.length() > 5&&password.length()<26);
    }

    private boolean validatePasswordConfirm(String passwordConfirm) {
        return (TextUtils.equals(passwordConfirm, editTextPasswordRegister.getText().toString())
                && passwordConfirm.length() > 5&&passwordConfirm.length()<26);
    }

    private boolean validateWeight(String weight){
        if(!TextUtils.isEmpty(weight)&&!weight.startsWith(".")){
            return (Double.parseDouble(weight) < 700 && Double.parseDouble(weight) > 0);
        }else{
            return false;
        }
    }
    private boolean validateHeight(String height){
        if(!TextUtils.isEmpty(height)&&!height.startsWith(".")) {
            return (Double.parseDouble(height) < 3 && Double.parseDouble(height) > 0);
        }else{
            return false;
        }
    }
    private boolean validatePhone(String phone){
        return phone.length()==10;
    }

    private ArrayList<String> gender() {
        genders = new ArrayList<String>() {{
            add(masculino);
            add(femenino);
        }};
        return genders;
    }
    private ArrayList<String> typeofsolution() {
        typesofsolution = new ArrayList<String>() {{
            add("1.5");
            add("2.5");
            add("4.5");
        }};
        return typesofsolution;
    }
    private ArrayList<String> typeoftreatment() {
        typesoftreatment = new ArrayList<String>() {{
            add(ambulatoria);
            add(ciclica);
        }};
        return typesoftreatment;
    }
    private ArrayList<String> extension() {
        extensions = new ArrayList<String>() {{
            add("+521");
        }};
        return extensions;
    }
    private void changeProgressDialog(String message){
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void showDatePickerDialogBirthday() {
        FollowFollowsFragment.DatePickerFragment newFragment = FollowFollowsFragment.DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                //final String selectedDate = year + "-" + (month+1) + "-" + day;
                date=new Date((year-1900),month,day);
                dateInString=dateFormat.format(date);
                try {
                    dateSelect=dateFormat.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(dateSelect.compareTo(dateNow)>0){
                    changeToast(fechanovalida,ic_harmful);
                    toast.show();
                }else{
                    dateBirth=dateSelect;
                    editTextDateOfBirthRegister.setText(dateInString);
                }
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void showDatePickerDialogCatheter() {
        FollowFollowsFragment.DatePickerFragment newFragment = FollowFollowsFragment.DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                //final String selectedDate = year + "-" + (month+1) + "-" + day;
                date=new Date((year-1900),month,day);
                dateInString=dateFormat.format(date);
                try {
                    dateSelect=dateFormat.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(dateSelect.compareTo(dateNow)>0||dateSelect.compareTo(dateBirth)<0){
                    changeToast(fechanovalida,ic_harmful);
                    toast.show();
                }else{
                    editTextDateCatheterRegister.setText(dateInString);
                }
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
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

    private double calculateImc(double height,double weight){
        return Math.round(((weight/(Math.pow(height,2))) * 100.0) / 100.0);
    }

    private void inicializarToast() {
        layoutInflater = getActivity().getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para cambiar los colores de la app
    private void setColorsApp(int color) {
        buttonRegister.setBackgroundColor(color);
    }

    //Metodo para insertar un nuevo usuario en la bd
    private void registerUser(final String correo, String password, String name, String genero, String picture,String dateOfBirthday,
                              double weight,double height,String dateCatheter,double typeOfSolution,double imc,String typeOfTreatment,
                              String emergencyContact) {
        kidneysService.createUserApi(correo, password, name, genero, picture,dateOfBirthday,weight,height,dateCatheter,
                typeOfSolution,imc,typeOfTreatment,emergencyContact).enqueue(new retrofit2.Callback<UserCreateApi>() {
            @Override
            public void onResponse(Call<UserCreateApi> call, Response<UserCreateApi> response) {
                progressDialog.dismiss();
                userCreateApi = response.body();
                if (TextUtils.equals(userCreateApi.getStatus(), "true")) {
                    changeToast(registro, ic_healthy);
                    toast.show();
                    closeFragment();
                } else if (TextUtils.equals(userCreateApi.getStatus(), "false")) {
                    changeToast(noregistro, ic_harmful);
                    toast.show();
                } else if (TextUtils.equals(userCreateApi.getStatus(), "already")) {
                    changeToast(correonodisponible, ic_harmful);
                    toast.show();
                } else {
                    changeToast(errorraro, ic_harmful);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<UserCreateApi> call, Throwable t) {
                progressDialog.dismiss();
                changeToast(errorconexion, ic_harmful);
                toast.show();
            }
        });
    }

    private void closeFragment() {
        callback.closeFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static FollowFollowsFragment.DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            FollowFollowsFragment.DatePickerFragment fragment = new FollowFollowsFragment.DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

    }
}
