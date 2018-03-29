package com.example.lesh.zonaverde.FragmentsRegistro;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lesh.zonaverde.Interfaces.Pages.EscuchandoPaginas;
import com.example.lesh.zonaverde.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoliticaFragment extends Fragment {
    //Variables globales
private Button politicaButton;
    private EscuchandoPaginas callback;

    public PoliticaFragment() {
        // Required empty public constructor
    }
    //Metodo que se inicializa antes que nada
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            //Inicializacion del callback de la interfaz
            callback=(EscuchandoPaginas) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_politica, container, false);
        //Inicializando componentes
        Inicializar(view);
        //Accion del boton
        politicaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaNumRegistro();
            }
        });
        return view;
    }
    //Metodo para inicializar los componentes
    private void Inicializar(View view){
        politicaButton=view.findViewById(R.id.politicaButton);
    }
    //Metodo para enviar el numero de registro a la actividad mediante la interfaz
    private void enviaNumRegistro(){
        callback.RecuperarNumPag(3);
    }
}
