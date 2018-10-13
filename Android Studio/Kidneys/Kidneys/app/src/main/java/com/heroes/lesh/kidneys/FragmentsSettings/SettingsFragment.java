package com.heroes.lesh.kidneys.FragmentsSettings;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.R;

import butterknife.BindDrawable;
import butterknife.BindFont;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    //Variables globales
    private View view;
    private ListenerPag callback;
    @BindView(R.id.checkBoxAzulSettings)
    CheckBox checkBoxAzulSettings;
    @BindView(R.id.checkBoxMarronSettings)
    CheckBox checkBoxMarronSettings;
    @BindView(R.id.checkBoxRosadoSettings)
    CheckBox checkBoxRosadoSettings;
    @BindView(R.id.floatingRefreshSettings)
    FloatingActionButton floatingRefreshSettings;
    @BindView(R.id.checkBoxCargarSettings)
    CheckBox checkBoxCargarSettings;
    @BindView(R.id.checkBoxNoCargarSettings)
    CheckBox checkBoxNoCargarSettings;
    @BindString(R.string.actualizar)
    String actualizar;
    @BindString(R.string.cargartoast)
    String cargartoast;
    @BindString(R.string.nocargartoast)
    String nocargartoast;
    @BindDrawable(R.mipmap.ic_settings)
    Drawable ic_settings;
    @BindFont(R.font.alice)
    Typeface alice;
    private Unbinder unbinder;
    private SharedPreferences preferences;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private int color;
    String cargar;
    SharedPreferences.Editor editor;

    public SettingsFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        changeFont();
        selectCheckBox();
        selectCheckBoxDatos();
        inicializarToast();
        checkBoxAzulSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Marcar y desmarcar
                if (isChecked) {
                    checkBoxMarronSettings.setChecked(false);
                    checkBoxRosadoSettings.setChecked(false);
                    color = 1;
                } else {
                    nothingSelected(color);
                }
            }
        });
        checkBoxMarronSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Marcar y desmarcar
                if (isChecked) {
                    checkBoxAzulSettings.setChecked(false);
                    checkBoxRosadoSettings.setChecked(false);
                    color = 2;
                } else {
                    nothingSelected(color);
                }
            }
        });
        checkBoxRosadoSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Marcar y desmarcar
                if (isChecked) {
                    checkBoxAzulSettings.setChecked(false);
                    checkBoxMarronSettings.setChecked(false);
                    color = 3;
                } else {
                    nothingSelected(color);
                }
            }
        });
        floatingRefreshSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerColor(color);
                refreshColor(color);
                //Animar el boton para que gire 360 grados
                ObjectAnimator.ofFloat(v, "rotation", 0, 360).start();
            }
        });
        floatingRefreshSettings.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(actualizar, ic_settings);
                toast.show();
                return false;
            }
        });
        checkBoxCargarSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxNoCargarSettings.setChecked(false);
                    if (!TextUtils.equals(cargar, "yes")) {
                        cargar = "yes";
                        registerCargar(cargar);
                        changeToast(cargartoast, ic_settings);
                        toast.show();
                    }
                } else {
                    nothingSelectedCargar(cargar);
                }
            }
        });
        checkBoxNoCargarSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxCargarSettings.setChecked(false);
                    if (!TextUtils.equals(cargar, "no")) {
                        cargar = "no";
                        registerCargar(cargar);
                        changeToast(nocargartoast, ic_settings);
                        toast.show();
                    }
                } else {
                    nothingSelectedCargar(cargar);
                }
            }
        });
        return view;
    }

    //Metodo para marcar casilla
    private void selectCheckBoxDatos() {
        cargar = preferences.getString("Cargar", "");
        switch (cargar) {
            case "no":
                checkBoxNoCargarSettings.setChecked(true);
                break;
            case "yes":
                checkBoxCargarSettings.setChecked(true);
                break;
        }
    }

    //Metodo para marcar el color seleccionado
    private void selectCheckBox() {
        color = preferences.getInt("ColorApp", 1);
        switch (color) {
            case 1:
                checkBoxAzulSettings.setChecked(true);
                break;
            case 2:
                checkBoxMarronSettings.setChecked(true);
                break;
            case 3:
                checkBoxRosadoSettings.setChecked(true);
                break;
        }
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    //Metodo para refescar el color de la app
    private void refreshColor(int color) {
        callback.refreshColor(color);
    }

    //Metodo para registrar el color
    private void registerColor(int color) {
        editor.putInt("ColorApp", color);
        editor.apply();
    }

    //Metodo para registrar el preferences de carga de datos
    private void registerCargar(String cargar) {
        editor.putString("Cargar", cargar);
        editor.apply();
    }

    //Metodo para inicializar el toast
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

    //Metodo para cambiar la fuente de los checkbox
    private void changeFont() {
        checkBoxAzulSettings.setTypeface(alice);
        checkBoxMarronSettings.setTypeface(alice);
        checkBoxRosadoSettings.setTypeface(alice);
        checkBoxCargarSettings.setTypeface(alice);
        checkBoxNoCargarSettings.setTypeface(alice);
    }

    //Metodo para que no que deseleccionado
    private void nothingSelectedCargar(String cargar) {
        if (!checkBoxCargarSettings.isChecked() && !checkBoxNoCargarSettings.isChecked()) {
            switch (cargar) {
                case "no":
                    checkBoxNoCargarSettings.setChecked(true);
                    break;
                case "yes":
                    checkBoxCargarSettings.setChecked(true);
                    break;
            }
        }
    }

    //Metodo para no dejar deseleccionado
    private void nothingSelected(int color) {
        if (!checkBoxAzulSettings.isChecked() && !checkBoxMarronSettings.isChecked() && !checkBoxRosadoSettings.isChecked()) {
            switch (color) {
                case 1:
                    checkBoxAzulSettings.setChecked(true);
                    break;
                case 2:
                    checkBoxMarronSettings.setChecked(true);
                    break;
                case 3:
                    checkBoxRosadoSettings.setChecked(true);
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
