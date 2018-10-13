package com.heroes.lesh.kidneys.FragmentsMain;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.R;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    //Variables globales
    private View view;
    private Unbinder unbinder;
    @BindView(R.id.card_view_main)
    CardView card_view_main;
    @BindView(R.id.floatingPhoneMain)
    FloatingActionButton floatingPhoneMain;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.mipmap.ic_kidneys)
    Drawable ic_kidneys;
    @BindString(R.string.llamar)
    String llamar;
    @BindString(R.string.llamando)
    String llamando;
    @BindString(R.string.nohaytelefono)
    String nohaytelefono;
    private SharedPreferences preferences;
    private String emergencyContact;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private Intent intent;
    private final int PERMISSION_CALL_PHONE = 1003;
    private int callphone;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        changeColors();
        phoneEmergencyPreferences();
        floatingPhoneMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals(emergencyContact, "")) {
                    changeToast(nohaytelefono,ic_harmful);
                    toast.show();
                } else {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            checkForPermisions();
                    }else{
                        intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+emergencyContact));
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                        changeToast(llamando,ic_healthy);
                        toast.show();
                    }
                }
            }
        });
        floatingPhoneMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(llamar,ic_kidneys);
                toast.show();
                return false;
            }
        });
        return view;
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    //Pedir permiso en ejecucion
    private void checkForPermisions() {
        callphone = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE);
        if (callphone != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CALL_PHONE);
        }
    }

    //Resultado de la pregunta del permiso
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CALL_PHONE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    } else {
                        intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+emergencyContact));
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                        changeToast(llamando,ic_healthy);
                        toast.show();
                    }
                }
                break;
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

    private void phoneEmergencyPreferences() {
        emergencyContact = preferences.getString("PhoneEmergency", "");
    }

    //Metodo para cambiar los colores de la app
    private void setColorsApp(int color) {
        card_view_main.setCardBackgroundColor(color);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
