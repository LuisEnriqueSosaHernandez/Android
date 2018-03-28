package com.example.lesh.zonaverde.FragmentsRegistro;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lesh.zonaverde.Api.Api;
import com.example.lesh.zonaverde.FragmentsRegistro.Modelos.Estados;
import com.example.lesh.zonaverde.Interfaces.Api.ZonaVerdeService;
import com.example.lesh.zonaverde.Interfaces.Pages.EscuchandoPaginas;
import com.example.lesh.zonaverde.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFragment extends Fragment {
    //Variables globales
    private Button registroButton;
    private EscuchandoPaginas callback;
    private Call<Estados> estadosCall;
    private ZonaVerdeService services;
    private ArrayAdapter<String> adapterEstados;
    private Estados estados;
    private ArrayList<String> listaEstados;
    private Spinner spinnerEstado;
    //private int idEstado=1;


    public RegistroFragment() {
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
        View view=inflater.inflate(R.layout.fragment_registro, container, false);
        //Metodo para inicializar
        Inicializar(view);
        //Evento del boton de la vista
        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaNumRegistro();
            }
        });
        //Metodo para llenar el spinner de estados
        estadosCall.enqueue(new Callback<Estados>() {
            @Override
            public void onResponse(Call<Estados> call, Response<Estados> response) {
                estados=response.body();
               for(int i=0;i<estados.getEstados().size();i++){
                    listaEstados.add(estados.getEstados().get(i).getEstado());
                }
                adapterEstados=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,listaEstados);
                spinnerEstado.setAdapter(adapterEstados);
            }

            @Override
            public void onFailure(Call<Estados> call, Throwable t) {
                Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    //Metodo para inicializar los componentes
    private void Inicializar(View view){
        registroButton=view.findViewById(R.id.registroButton);
        services= Api.getApi().create(ZonaVerdeService.class);
        estadosCall=services.getEstados("Estados");
        estados=null;
        listaEstados=new ArrayList<>();
        adapterEstados=null;
        spinnerEstado=view.findViewById(R.id.spinnerEstado);


    }
    //Metodo para enviar el numero de registro a la actividad mediante la interfaz
    private void enviaNumRegistro(){
        callback.RecuperarNumPag(5);
    }
}
