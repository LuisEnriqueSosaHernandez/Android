package com.example.lesh.zonaverde.FragmentsRegistro;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lesh.zonaverde.Interfaces.Pages.EscuchandoPaginas;
import com.example.lesh.zonaverde.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MotivosFragment extends Fragment {
    //Variables globales
    private Button motivosButton;
    private TextView textMotivos;
    private EscuchandoPaginas callback;

    public MotivosFragment() {
        // Required empty public constructor
    }

    //Metodo que se ejecuta al principio de todo
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
        View view=inflater.inflate(R.layout.fragment_motivos, container, false);
        //Metodo para inicializar
        Inicializar(view);
        //Evento del boton de la vista
        motivosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaNumRegistro();
            }
        });
        //Evento del texto de la vista
        textMotivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retornar();
            }
        });
        return view;
    }
    //Metodo para inicializar los componentes
    private void Inicializar(View view){
        motivosButton=view.findViewById(R.id.motivosButton);
        textMotivos=view.findViewById(R.id.textMotivos);
    }
    //Metodo para enviar el numero de registro a la actividad mediante la interfaz
    private void enviaNumRegistro(){
        callback.RecuperarNumPag(4);
    }
    //Metodo para salir a main
    private void retornar(){
        callback.RecuperarNumPag(10);
    }
}
