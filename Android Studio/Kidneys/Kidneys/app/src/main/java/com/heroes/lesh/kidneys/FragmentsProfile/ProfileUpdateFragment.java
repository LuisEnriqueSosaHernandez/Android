package com.heroes.lesh.kidneys.FragmentsProfile;


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
import com.heroes.lesh.kidneys.Models.ModelsApi.UserUpdatePasswordApi;
import com.heroes.lesh.kidneys.R;

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
public class ProfileUpdateFragment extends Fragment {
    //Variables globales
    private View view;
    @BindView(R.id.editTextNameUpdateProfile)
    EditText editTextNameUpdateProfile;
    @BindView(R.id.editTextWeightUpdateProfile)
    EditText editTextWeightUpdateProfile;
    @BindView(R.id.editTextHeightUpdateProfile)
    EditText editTextHeightUpdateProfile;
    @BindView(R.id.editTextDateOfBirthUpdateProfile)
    EditText editTextDateOfBirthUpdateProfile;
    @BindView(R.id.editTextDateCatheterUpdateProfile)
    EditText editTextDateCatheterUpdateProfile;
    @BindView(R.id.editTextContactEmergencyUpdate)
    EditText editTextContactEmergencyUpdate;
    @BindView(R.id.spinnerRegister)
    Spinner spinnerRegister;
    @BindView(R.id.spinnerUpdateTypeOfSolution)
    Spinner spinnerUpdateTypeOfSolution;
    @BindView(R.id.spinnerUpdateTypeOfTreatment)
    Spinner spinnerUpdateTypeOfTreatment;
    @BindView(R.id.spinnerUpdateExtension)
    Spinner spinnerUpdateExtension;
    @BindView(R.id.buttonUpdateProfile)
    Button buttonUpdateProfile;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.mipmap.ic_profile)
    Drawable ic_profile;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindString(R.string.actualizarperfil)
    String actualizarperfil;
    @BindString(R.string.fechanovalida)
    String fechanovalida;
    @BindString(R.string.masculino)
    String masculino;
    @BindString(R.string.femenino)
    String femenino;
    @BindString(R.string.nombreinvalido)
    String nombreinvalido;
    @BindString(R.string.pesoinvalido)
    String pesoinvalido;
    @BindString(R.string.alturainvalida)
    String alturainvalida;
    @BindString(R.string.correovacio)
    String correovacio;
    @BindString(R.string.errorraro)
    String errorraro;
    @BindString(R.string.errorconexion)
    String errorconexion;
    @BindString(R.string.perfilactualizado)
    String perfilactualizado;
    @BindString(R.string.perfilnoactualizado)
    String perfilnoactualizado;
    @BindString(R.string.reintentardatos)
    String reintentardatos;
    @BindString(R.string.ambulatoria)
    String ambulatoria;
    @BindString(R.string.ciclica)
    String ciclica;
    @BindString(R.string.telefonoinvalido)
    String telefonoinvalido;
    @BindString(R.string.actualizando)
    String actualizando;
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
    private Unbinder unbinder;
    ArrayList<String> genders;
    ArrayList<String> typesofsolution;
    ArrayList<String> typesoftreatment;
    ArrayList<String> extensions;
    private SharedPreferences preferences;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private KidneysService kidneysService;
    private UserUpdatePasswordApi userUpdatePasswordApi;
    private ListenerPag callback;
    private String correo;
    private SimpleDateFormat dateFormat;
    private Date date;
    private String dateInString;
    private Date dateNow;
    private Date dateSelect;
    private Date dateBirth;
    private String name;
    private String genero;
    private String dateOfBirthday;
    private double weight;
    private double height;
    private String dateCatheter;
    private double typeOfSolution;
    private double imc;
    private String typeOfTreatment;
    private String emergencyContact;
    private Bundle bundle;
    private ProgressDialog progressDialog;

    public ProfileUpdateFragment() {
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
        view = inflater.inflate(R.layout.fragment_profile_update, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        //Cambiar colores
        changeColors();
        correoPreferences();
        spinnerRegister.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, genders));
        spinnerRegister.getSelectedItem().toString();
        spinnerUpdateTypeOfSolution.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, typesofsolution));
        spinnerUpdateTypeOfSolution.getSelectedItem().toString();
        spinnerUpdateTypeOfTreatment.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, typesoftreatment));
        spinnerUpdateTypeOfTreatment.getSelectedItem().toString();
        spinnerUpdateExtension.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_register, extensions));
        spinnerUpdateExtension.getSelectedItem().toString();
        bundle = getArguments();
        if (bundle != null) {
            correo = bundle.getString("Correo");
            name = bundle.getString("Name");
            genero = bundle.getString("Genero");
            dateOfBirthday = bundle.getString("DateOfBirthday");
            weight = bundle.getDouble("Weight");
            height = bundle.getDouble("Height");
            dateCatheter = bundle.getString("DateCatheter");
            typeOfSolution = bundle.getDouble("TypeOfSolution");
            imc = bundle.getDouble("Imc");
            typeOfTreatment = bundle.getString("TypeOfTreatment");
            emergencyContact = bundle.getString("EmergencyContact");
            changeData(name, genero, dateOfBirthday, weight, height, dateCatheter, typeOfSolution, typeOfTreatment, emergencyContact);
        } else {
            changeToast(reintentardatos, ic_profile);
            toast.show();
            //tryAgainCargarTablaUser();
        }
        editTextNameUpdateProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validateName(editTextNameUpdateProfile.getText().toString()) && !TextUtils.equals(editTextNameUpdateProfile.getText().toString(), "")) {
                        changeToast(nombreinvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextNameUpdateProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateName(s.toString())) {
                    editTextNameUpdateProfile.setTextColor(colorNegro);
                    editTextNameUpdateProfile.setHintTextColor(colorNegro);
                } else {
                    editTextNameUpdateProfile.setTextColor(colorRojo);
                    editTextNameUpdateProfile.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextWeightUpdateProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validateWeight(editTextWeightUpdateProfile.getText().toString()) && !TextUtils.equals(editTextWeightUpdateProfile.getText().toString(), "")) {
                        changeToast(pesoinvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextWeightUpdateProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateWeight(s.toString())) {
                    editTextWeightUpdateProfile.setTextColor(colorNegro);
                    editTextWeightUpdateProfile.setHintTextColor(colorNegro);
                } else {
                    editTextWeightUpdateProfile.setTextColor(colorRojo);
                    editTextWeightUpdateProfile.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextHeightUpdateProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validateHeight(editTextHeightUpdateProfile.getText().toString()) && !TextUtils.equals(editTextHeightUpdateProfile.getText().toString(), "")) {
                        changeToast(alturainvalida, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextHeightUpdateProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateHeight(s.toString())) {
                    editTextHeightUpdateProfile.setTextColor(colorNegro);
                    editTextHeightUpdateProfile.setHintTextColor(colorNegro);
                } else {
                    editTextHeightUpdateProfile.setTextColor(colorRojo);
                    editTextHeightUpdateProfile.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextDateOfBirthUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogBirthday();
            }
        });
        editTextDateOfBirthUpdateProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        editTextDateCatheterUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogCatheter();
            }
        });
        editTextDateCatheterUpdateProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        editTextContactEmergencyUpdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validatePhone(editTextContactEmergencyUpdate.getText().toString()) && !TextUtils.equals(editTextContactEmergencyUpdate.getText().toString(), "")) {
                        changeToast(telefonoinvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextContactEmergencyUpdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePhone(s.toString())) {
                    editTextContactEmergencyUpdate.setTextColor(colorNegro);
                    editTextContactEmergencyUpdate.setHintTextColor(colorNegro);
                } else {
                    editTextContactEmergencyUpdate.setTextColor(colorRojo);
                    editTextContactEmergencyUpdate.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail(correo)) {
                    if (validateName(editTextNameUpdateProfile.getText().toString())) {
                        if (validateWeight(editTextWeightUpdateProfile.getText().toString())) {
                            if (validateHeight(editTextHeightUpdateProfile.getText().toString())) {
                                name = editTextNameUpdateProfile.getText().toString().trim();
                                weight = Double.parseDouble(editTextWeightUpdateProfile.getText().toString().trim());
                                height = Double.parseDouble(editTextHeightUpdateProfile.getText().toString().trim());
                                dateOfBirthday = editTextDateOfBirthUpdateProfile.getText().toString().trim();
                                dateCatheter = editTextDateCatheterUpdateProfile.getText().toString().trim();
                                if (validatePhone(editTextContactEmergencyUpdate.getText().toString())) {
                                    if (TextUtils.equals(spinnerRegister.getSelectedItem().toString(), masculino)) {
                                        genero = "M";
                                    } else {
                                        genero = "F";
                                    }
                                    typeOfSolution = Double.parseDouble(spinnerUpdateTypeOfSolution.getSelectedItem().toString());
                                    typeOfTreatment = spinnerUpdateTypeOfTreatment.getSelectedItem().toString();
                                    imc = calculateImc(height, weight);
                                    emergencyContact = phoneEmergency(spinnerUpdateExtension.getSelectedItem().toString(),
                                            editTextContactEmergencyUpdate.getText().toString()).trim();
                                    changeProgressDialog(actualizando);
                                    progressDialog.show();
                                    updateProfile(correo, name, genero, weight, height, dateOfBirthday, dateCatheter, typeOfSolution, imc, typeOfTreatment, emergencyContact);
                                } else {
                                    changeToast(telefonoinvalido, ic_harmful);
                                    toast.show();
                                }
                            } else {
                                changeToast(alturainvalida, ic_harmful);
                                toast.show();
                            }
                        } else {
                            changeToast(pesoinvalido, ic_harmful);
                            toast.show();
                        }

                    } else {
                        changeToast(nombreinvalido, ic_harmful);
                        toast.show();
                    }
                } else {
                    changeToast(correovacio, ic_harmful);
                    toast.show();
                }
            }
        });
        buttonUpdateProfile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(actualizarperfil, ic_profile);
                toast.show();
                return false;
            }
        });
        return view;
    }

    private String phoneEmergency(String extension, String phone) {
        return extension + phone;
    }

    private void changeData(String name, String genero, String dateOfBirthday, double weight, double height, String dateCatheter,
                            double typeOfSolution, String typeOfTreatment, String emergencyContact) {
        editTextNameUpdateProfile.setText(name);
        if (weight != 0) {
            editTextWeightUpdateProfile.setText("" + weight);
        }
        if (height != 0) {
            editTextHeightUpdateProfile.setText("" + height);
        }
        if (!TextUtils.equals(dateOfBirthday, "0000-00-00")) {
            editTextDateOfBirthUpdateProfile.setText(dateOfBirthday);
        }
        if (!TextUtils.equals(dateCatheter, "0000-00-00")) {
            editTextDateCatheterUpdateProfile.setText(dateCatheter);
        }
        if (TextUtils.equals(genero, "M")) {
            spinnerRegister.setSelection(0);
        } else if (TextUtils.equals(genero, "F")) {
            spinnerRegister.setSelection(1);
        }
        if (typeOfSolution == 1.5) {
            spinnerUpdateTypeOfSolution.setSelection(0);
        } else if (typeOfSolution == 2.5) {
            spinnerUpdateTypeOfSolution.setSelection(1);
        } else if (typeOfSolution == 4.5) {
            spinnerUpdateTypeOfSolution.setSelection(2);
        }

        if (TextUtils.equals(typeOfTreatment, ambulatoria)) {
            spinnerUpdateTypeOfTreatment.setSelection(0);
        } else if (TextUtils.equals(typeOfTreatment, ciclica)) {
            spinnerUpdateTypeOfTreatment.setSelection(1);
        }
        editTextContactEmergencyUpdate.setText(cutNumber(emergencyContact));
    }

    //Metodo para validar el nombre
    private boolean validateName(String name) {
        return (name.length() > 5);
    }

    //Metodo para validar el peso
    private boolean validateWeight(String weight) {
        if (!TextUtils.isEmpty(weight) && !weight.startsWith(".")) {
            return (Double.parseDouble(weight) < 700 && Double.parseDouble(weight) > 0);
        } else {
            return false;
        }
    }

    //Metodo para validar la altura
    private boolean validateHeight(String height) {
        if (!TextUtils.isEmpty(height) && !height.startsWith(".")) {
            return (Double.parseDouble(height) < 3 && Double.parseDouble(height) > 0);
        } else {
            return false;
        }
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

    private double calculateImc(double height, double weight) {
        return Math.round(((weight / (Math.pow(height, 2))) * 100.0) / 100.0);
    }

    //Quitar extension numero
    private String cutNumber(String emergencyContact) {
        if (!TextUtils.isEmpty(emergencyContact)) {
            return emergencyContact.substring(4);
        }
        return emergencyContact;
    }

    private boolean validatePhone(String phone) {
        return phone.length() == 10;
    }

    private void showDatePickerDialogBirthday() {
        FollowFollowsFragment.DatePickerFragment newFragment = FollowFollowsFragment.DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                //final String selectedDate = year + "-" + (month+1) + "-" + day;
                date = new Date((year - 1900), month, day);
                dateInString = dateFormat.format(date);
                try {
                    dateSelect = dateFormat.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (dateSelect.compareTo(dateNow) > 0) {
                    changeToast(fechanovalida, ic_harmful);
                    toast.show();
                } else {
                    dateBirth = dateSelect;
                    editTextDateOfBirthUpdateProfile.setText(dateInString);
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
                date = new Date((year - 1900), month, day);
                dateInString = dateFormat.format(date);
                try {
                    dateSelect = dateFormat.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (dateSelect.compareTo(dateNow) > 0 || dateSelect.compareTo(dateBirth) < 0) {
                    changeToast(fechanovalida, ic_harmful);
                    toast.show();
                } else {
                    editTextDateCatheterUpdateProfile.setText(dateInString);
                }
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        genders = gender();
        typesofsolution = typeofsolution();
        typesoftreatment = typeoftreatment();
        extensions = extension();
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        kidneysService = Api.getApi().create(KidneysService.class);
        editTextDateOfBirthUpdateProfile.setInputType(InputType.TYPE_NULL);
        editTextDateCatheterUpdateProfile.setInputType(InputType.TYPE_NULL);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date = new Date();
        dateInString = dateFormat.format(date);
        try {
            dateNow = dateFormat.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(dateInString)) {
            editTextDateOfBirthUpdateProfile.setText(dateInString);
            editTextDateCatheterUpdateProfile.setText(dateInString);
        }
        dateBirth = dateNow;
        progressDialog = new ProgressDialog(getActivity());
    }


    //Metodo para validar correo
    private boolean validateEmail(String correo) {
        return (TextUtils.equals(correo, "") || TextUtils.equals(correo, "no email"));

    }

    //Metodo para recuperar el correo de las shared
    private void correoPreferences() {
        correo = preferences.getString("Email", "");
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

    private void inicializarToast() {
        layoutInflater = getActivity().getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    private void changeProgressDialog(String message) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para cambiar los colores de la app
    private void setColorsApp(int color) {
        buttonUpdateProfile.setBackgroundColor(color);
    }
    //Metodo para actualizar el perfil

    private void updateProfile(String correo, String name, String gender, double weight, double height, String dateOfBirthday, String dateCatheter, double typeOfSolution,
                               double imc, String typeOfTreatment, String emergencyContact) {
        kidneysService.updateUserProfiledApi(correo, name, gender, dateOfBirthday, weight, height, dateCatheter, typeOfSolution
                , imc, typeOfTreatment, emergencyContact).enqueue(new retrofit2.Callback<UserUpdatePasswordApi>() {
            @Override
            public void onResponse(Call<UserUpdatePasswordApi> call, Response<UserUpdatePasswordApi> response) {
                progressDialog.dismiss();
                userUpdatePasswordApi = response.body();
                if (TextUtils.equals(userUpdatePasswordApi.getStatus(), "true")) {
                    changeToast(perfilactualizado, ic_healthy);
                    toast.show();
                    closeFragment();
                } else if (TextUtils.equals(userUpdatePasswordApi.getStatus(), "false")) {
                    changeToast(perfilnoactualizado, ic_harmful);
                    toast.show();
                } else if (TextUtils.equals(userUpdatePasswordApi.getStatus(), "incorrect")) {
                    changeToast(correovacio, ic_harmful);
                    toast.show();
                } else {
                    changeToast(errorraro, ic_harmful);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<UserUpdatePasswordApi> call, Throwable t) {
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
    public void onDestroyView() {
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        super.onDestroyView();
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
