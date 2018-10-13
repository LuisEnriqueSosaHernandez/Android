package com.heroes.lesh.kidneys.FragmentsWelcome;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeNinethFragment extends Fragment {
    //variables globales
    private ListenerPag callback;
    private View view;
    @BindView(R.id.endWelcome)
    Button endWelcome;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    private Unbinder unbinder;
    private SharedPreferences preferences;
    SharedPreferences.Editor editor;
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

    public WelcomeNinethFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_welcome_nineth, container, false);
        unbinder = ButterKnife.bind(this, view);
        //evento del boton de cierre de viewpager
        inicializar();
        changeColors();
        endWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePreferences();
                closeWelcome();
            }
        });
        return view;
    }
    private void inicializar(){
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    //Metodo para verificar que vio el welcome
    private void changePreferences() {
        editor.putInt("WatchWelcome", 2);
        editor.apply();
    }

    //Metodo para cerrar la actividad
    private void closeWelcome() {
        callback.closeViewPagerAndOpenActivity();
    }
    //Metodo para recuperar el color requerido
    private void changeColors() {
        switch (preferences.getInt("ColorApp", 1)) {
            case 1:
                endWelcome.setBackgroundColor(colorAzul);
                break;
            case 2:
                endWelcome.setBackgroundColor(colorMarron);
                break;
            case 3:
                endWelcome.setBackgroundColor(colorRosado);
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
