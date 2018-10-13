package com.heroes.lesh.kidneys.FragmentsHelp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {
    //Variables globales
    private ListenerPag callback;
    @BindView(R.id.card_view_help_questions_card)
    CardView card_view_help_questions_card;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    private View view;
    private Unbinder unbinder;
    private SharedPreferences preferences;

    public HelpFragment() {
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
        view = inflater.inflate(R.layout.fragment_help, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        changeColors();
        card_view_help_questions_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment(11);
            }
        });
        return view;
    }

    //Metodo para inicializar componentes
    private void inicializar() {
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
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
        card_view_help_questions_card.setCardBackgroundColor(color);
    }

    private void sendFragment(int num) {
        callback.openFragment(num);
    }

    //Metodo para destruir el bin de los elementos
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
