package com.heroes.lesh.kidneys.FragmentsTreatments;


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
public class TreatmentsFragment extends Fragment {
    //Variables globales
    private ListenerPag callback;
    @BindView(R.id.card_view_treatments_guides_card)
    CardView card_view_treatments_guides_card;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    private Unbinder unbinder;
    private View view;
    private SharedPreferences preferences;

    public TreatmentsFragment() {
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
        view = inflater.inflate(R.layout.fragment_treatments, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        changeColors();
        //Evento del boton
        card_view_treatments_guides_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendFragment(1);
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
        card_view_treatments_guides_card.setCardBackgroundColor(color);
    }

    //Metodo sobreescrito de la interfaz para cambiar fragment
    private void SendFragment(int num) {
        callback.openFragment(num);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
