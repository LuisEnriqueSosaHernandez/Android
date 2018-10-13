package com.heroes.lesh.kidneys.FragmentsProfile;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.ModelsApi.UserUpdatePasswordApi;
import com.heroes.lesh.kidneys.R;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class
ProfilePasswordFragment extends Fragment {
    //Variables globales
    private View view;
    private Unbinder unbinder;
    @BindView(R.id.editTextPasswordProfile)
    EditText editTextPasswordProfile;
    @BindView(R.id.editTextNewPasswordProfile)
    TextView editTextNewPasswordProfile;
    @BindView(R.id.editTextNewPasswordConfirmProfile)
    TextView editTextNewPasswordConfirmProfile;
    @BindView(R.id.buttonUpdatePasswordProfile)
    Button buttonUpdatePasswordProfile;
    @BindString(R.string.cambiarcontra)
    String cambiarcontra;
    @BindString(R.string.contrainvalido)
    String contrainvalido;
    @BindString(R.string.contranocoincide)
    String contranocoincide;
    @BindString(R.string.correovacio)
    String correovacio;
    @BindString(R.string.contraincorrecta)
    String contraincorrecta;
    @BindString(R.string.contraactualizada)
    String contraactualizada;
    @BindString(R.string.contranoactualizada)
    String contranoactualizada;
    @BindString(R.string.errorconexion)
    String errorconexion;
    @BindString(R.string.errorraro)
    String errorraro;
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
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.mipmap.ic_profile)
    Drawable ic_profile;
    private SharedPreferences preferences;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private KidneysService kidneysService;
    private UserUpdatePasswordApi userUpdatePasswordApi;
    private ListenerPag callback;
    private String password;
    private String newPassword;
    private String correo;
    private ProgressDialog progressDialog;

    public ProfilePasswordFragment() {
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
        view = inflater.inflate(R.layout.fragment_profile_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        //Cambiar colores
        changeColors();
        correoPreferences();
        editTextPasswordProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validatePassword(editTextPasswordProfile.getText().toString()) && !TextUtils.equals(editTextPasswordProfile.getText().toString(), "")) {
                        changeToast(contrainvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextPasswordProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePassword(s.toString())) {
                    editTextPasswordProfile.setTextColor(colorNegro);
                    editTextPasswordProfile.setHintTextColor(colorNegro);
                } else {
                    editTextPasswordProfile.setTextColor(colorRojo);
                    editTextPasswordProfile.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextNewPasswordProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validateNewPassword(editTextNewPasswordProfile.getText().toString()) && !TextUtils.equals(editTextNewPasswordProfile.getText().toString(), "")) {
                        changeToast(contrainvalido, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextNewPasswordProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateNewPassword(s.toString())) {
                    editTextNewPasswordProfile.setTextColor(colorNegro);
                    editTextNewPasswordProfile.setHintTextColor(colorNegro);
                } else {
                    editTextNewPasswordProfile.setTextColor(colorRojo);
                    editTextNewPasswordProfile.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextNewPasswordConfirmProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validatePasswordConfirm(editTextNewPasswordConfirmProfile.getText().toString()) && !TextUtils.equals(editTextNewPasswordConfirmProfile.getText().toString(), "")) {
                        changeToast(contranocoincide, ic_harmful);
                        toast.show();
                    }
                }
            }
        });
        editTextNewPasswordConfirmProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePasswordConfirm(s.toString())) {
                    editTextNewPasswordConfirmProfile.setTextColor(colorNegro);
                    editTextNewPasswordConfirmProfile.setHintTextColor(colorNegro);
                } else {
                    editTextNewPasswordConfirmProfile.setTextColor(colorRojo);
                    editTextNewPasswordConfirmProfile.setHintTextColor(colorRojo);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        buttonUpdatePasswordProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePassword(editTextPasswordProfile.getText().toString())) {
                    if (validateNewPassword(editTextNewPasswordProfile.getText().toString())) {
                        if (validatePasswordConfirm(editTextNewPasswordConfirmProfile.getText().toString())) {
                            if (!validateEmail(correo)) {
                                password = editTextPasswordProfile.getText().toString();
                                if(!TextUtils.isEmpty(password)){
                                    try {
                                        password = toSHA1(password.getBytes("UTF-8"));
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                                newPassword = editTextNewPasswordConfirmProfile.getText().toString();
                                try {
                                    newPassword = toSHA1(newPassword.getBytes("UTF-8"));
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                changeProgressDialog(actualizando);
                                progressDialog.show();
                                updatePassword(correo,password,newPassword);
                            } else {
                                changeToast(correovacio, ic_harmful);
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
                    changeToast(contrainvalido, ic_harmful);
                    toast.show();
                }
            }
        });
        buttonUpdatePasswordProfile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(cambiarcontra,ic_profile);
                toast.show();
                return false;
            }
        });
        return view;
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        kidneysService = Api.getApi().create(KidneysService.class);
        progressDialog = new ProgressDialog(getActivity());
    }

    private boolean validatePassword(String password) {
        return ((password.length() > 5 && password.length() < 26) || TextUtils.equals(password, ""));
    }

    private boolean validateNewPassword(String newPassword) {
        return (newPassword.length() > 5 && newPassword.length() < 26);
    }

    private boolean validatePasswordConfirm(String passwordNewConfirm) {
        return (TextUtils.equals(passwordNewConfirm, editTextNewPasswordProfile.getText().toString())
                && passwordNewConfirm.length() > 5 && passwordNewConfirm.length() < 26);
    }

    //Metodo para recuperar el correo de las shared
    private void correoPreferences() {
        correo = preferences.getString("Email", "");
    }

    //Metodo para validar correo
    private boolean validateEmail(String correo) {
        return (TextUtils.equals(correo, "") || TextUtils.equals(correo, "no email"));

    }

    //Metodo para encriptar
    public static String toSHA1(byte[] convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteArrayToHexString(md.digest(convertme));
    }

    //Metodo para convertir de hex
    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result +=
                    Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
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
        buttonUpdatePasswordProfile.setBackgroundColor(color);
    }

    private void updatePassword(String email, String password, String newPassword) {
        kidneysService.updateUserPassswordApi(email, password, newPassword).enqueue(new retrofit2.Callback<UserUpdatePasswordApi>() {
            @Override
            public void onResponse(Call<UserUpdatePasswordApi> call, Response<UserUpdatePasswordApi> response) {
                progressDialog.dismiss();
                userUpdatePasswordApi = response.body();
                if (TextUtils.equals(userUpdatePasswordApi.getStatus(), "true")) {
                    changeToast(contraactualizada, ic_healthy);
                    toast.show();
                    closeFragment();
                } else if (TextUtils.equals(userUpdatePasswordApi.getStatus(), "false")) {
                    changeToast(contranoactualizada, ic_harmful);
                    toast.show();
                } else if (TextUtils.equals(userUpdatePasswordApi.getStatus(), "incorrect")) {
                    changeToast(contraincorrecta, ic_harmful);
                    toast.show();
                    editTextPasswordProfile.setTextColor(colorRojo);
                    editTextPasswordProfile.setHintTextColor(colorRojo);
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

}
