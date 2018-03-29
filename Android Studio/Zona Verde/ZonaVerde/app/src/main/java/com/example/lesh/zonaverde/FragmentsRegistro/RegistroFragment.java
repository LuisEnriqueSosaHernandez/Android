package com.example.lesh.zonaverde.FragmentsRegistro;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lesh.zonaverde.Api.Api;
import com.example.lesh.zonaverde.FragmentsRegistro.Modelos.Estados;
import com.example.lesh.zonaverde.FragmentsRegistro.Modelos.Municipios;
import com.example.lesh.zonaverde.FragmentsRegistro.Modelos.Suelos;
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
    private ZonaVerdeService services;
    private Call<Estados> estadosCall;
    private ArrayAdapter<String> adapterEstados;
    private Estados estados;
    private ArrayList<String> listaEstados;
    private Spinner spinnerEstado;
    private int idEstado;
    private Call<Municipios> municipiosCall;
    private ArrayAdapter<String> adapterMunicipios;
    private Municipios municipios;
    private ArrayList<String> listaMunicipios;
    private Spinner spinnerMunicipio;
    private int idMunicipio;
    private Call<Suelos> suelosCall;
    private ArrayAdapter<String> adapterSuelos;
    private Suelos suelos;
    private ArrayList<String> listaSuelos;
    private Spinner spinnerSuelo;
    private int idSuelo;



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
        //Evento del spinner de la vista para los estados
        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,final View view, int position, long id) {
                listaMunicipios.clear();
                idEstado=position+1;
                municipiosCall=services.getMunicipios("Municipios",idEstado);
                municipiosCall.enqueue(new Callback<Municipios>() {
                    @Override
                    public void onResponse(Call<Municipios> call, Response<Municipios> response) {
                        municipios=response.body();
                        for (int i=0;i<municipios.getMunicipios().size();i++){
                            listaMunicipios.add(municipios.getMunicipios().get(i).getMunicipio());
                        }
                        adapterMunicipios=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listaMunicipios);
                        spinnerMunicipio.setAdapter(adapterMunicipios);
                    }

                    @Override
                    public void onFailure(Call<Municipios> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Evento del spinner de la vista para los municipios
        spinnerMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
                idMunicipio=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Evento del spinner de la vista para los tipos de suelos
        spinnerSuelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
                idSuelo=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
   //Metodo para llenar el spinner de tipos de suelos
   suelosCall.enqueue(new Callback<Suelos>() {
       @Override
       public void onResponse(Call<Suelos> call, Response<Suelos> response) {
            suelos=response.body();
            for (int i=0;i<suelos.getSuelos().size();i++){
                listaSuelos.add(suelos.getSuelos().get(i).getSuelo());
            }
            adapterSuelos=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,listaSuelos);
           spinnerSuelo.setAdapter(adapterSuelos);
       }

       @Override
       public void onFailure(Call<Suelos> call, Throwable t) {
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
        municipiosCall=null;
        municipios=null;
        listaMunicipios=new ArrayList<>();
        adapterMunicipios=null;
        spinnerMunicipio=view.findViewById(R.id.spinnerMunicipio);
        suelosCall=services.getSuelos("Suelos");
        suelos=null;
        listaSuelos=new ArrayList<>();
        adapterSuelos=null;
        spinnerSuelo=view.findViewById(R.id.spinnerSuelo);
    }
    //Metodo para enviar el numero de registro a la actividad mediante la interfaz
    private void enviaNumRegistro(){
        callback.RecuperarNumPag(5);
    }
}
