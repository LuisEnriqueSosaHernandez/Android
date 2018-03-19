package com.example.lesh.zonaverde.FragmentsRegistro;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lesh.zonaverde.Activities.BienvenidoActivity;
import com.example.lesh.zonaverde.Interfaces.Pages.EscuchandoPaginas;
import com.example.lesh.zonaverde.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BienvenidoFragment extends Fragment {
private Button bienvenidoButton;
private EscuchandoPaginas callback;

    public BienvenidoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
                callback=(EscuchandoPaginas) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_bienvenido, container, false);
        Inicializar(view);
        bienvenidoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaNumRegistro();
            }
        });
        return view;
    }
    private void Inicializar(View view){
        bienvenidoButton=view.findViewById(R.id.bienvenidoButton);
    }
    private void enviaNumRegistro(){
        callback.RecuperarNumPag(2);
    }
}
