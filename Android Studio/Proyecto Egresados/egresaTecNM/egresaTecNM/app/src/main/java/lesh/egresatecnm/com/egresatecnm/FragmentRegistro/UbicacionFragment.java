package lesh.egresatecnm.com.egresatecnm.FragmentRegistro;


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

import java.util.ArrayList;


import lesh.egresatecnm.com.egresatecnm.Api.Api;

import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos.Carreras;
import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos.Estados;

import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos.Tecnologicos;
import lesh.egresatecnm.com.egresatecnm.Interfaces.Api.egresaTecNMService;
import lesh.egresatecnm.com.egresatecnm.Interfaces.ListenerPag;
import lesh.egresatecnm.com.egresatecnm.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UbicacionFragment extends Fragment {
    private ListenerPag callback;
    private Button btnBackUbicacion;
    private Button btnNextUbicacion;
    private egresaTecNMService services;
    private ArrayAdapter<String> adapterEstados;
    private Call<Estados> estadosCall;
    private Estados estados;
    private ArrayList<String> listaEstados;
    private ArrayAdapter<String> adapterTecnologicos;
    private Call<Tecnologicos> tecnologicosCall;
    private Tecnologicos tecnologicos;
    private ArrayList listaTecnologicos;
    private ArrayAdapter<String> adapterCarreras;
    private Call<Carreras> carrerasCall;
    private Carreras carreras;
    private ArrayList<String> listaCarreras;
    private Spinner spinnerEstado;
    private Spinner spinnerTecnologico;
    private Spinner spinnerCarrera;
    private int idEstado=1;
    private int idTecnologico=1;
    private int idCarrera=1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback=(ListenerPag) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString()+"algo salio mal");
        }
    }
    public UbicacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_ubicacion, container, false);
        Inicializar(view);
        btnBackUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaNumUbicacionRegresa();
            }
        });
        btnNextUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaNumUbicacion();
            }
        });
      spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
              listaTecnologicos.clear();
              listaCarreras.clear();
              idEstado=position+1;
              tecnologicosCall=services.getTecnologicos("obtenerTecnologicos",Integer.toString(idEstado));
            tecnologicosCall.enqueue(new Callback<Tecnologicos>() {
                @Override
                public void onResponse(Call<Tecnologicos> call, Response<Tecnologicos> response) {
                    tecnologicos=response.body();
                    for (int i=0;i<tecnologicos.getTecnologicos().size();i++){
                        listaTecnologicos.add(tecnologicos.getTecnologicos().get(i).getTecnologico());
                    }


                    adapterTecnologicos=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listaTecnologicos);
                    spinnerTecnologico.setAdapter(adapterTecnologicos);
                }

                @Override
                public void onFailure(Call<Tecnologicos> call, Throwable t) {
                    Toast.makeText(getContext(), R.string.conexion, Toast.LENGTH_SHORT).show();
                }
            });
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
          }
      });
        spinnerTecnologico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
                listaCarreras.clear();
              idTecnologico=Integer.parseInt(tecnologicos.getTecnologicos().get(position).getIdTecnologico());
                //Toast.makeText(view.getContext(), tecnologicos.getTecnologicos().get(position).getIdTecnologico(), Toast.LENGTH_SHORT).show();
                carrerasCall=services.getCarreras("obtenerCarreras",Integer.toString(idEstado),Integer.toString(idTecnologico));
                carrerasCall.enqueue(new Callback<Carreras>() {
                    @Override
                    public void onResponse(Call<Carreras> call, Response<Carreras> response) {
                      carreras=response.body();
                        for(int i=0;i<carreras.getCarreras().size();i++){
                            listaCarreras.add(carreras.getCarreras().get(i).getNcarrera());
                        }
                        adapterCarreras=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listaCarreras);
                        spinnerCarrera.setAdapter(adapterCarreras);
                    }

                    @Override
                    public void onFailure(Call<Carreras> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.conexion, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
               idCarrera=Integer.parseInt(carreras.getCarreras().get(position).getIdCarrera());
               // Toast.makeText(view.getContext(),carreras.getCarreras().get(position).getIdCarrera() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        estadosCall.enqueue(new Callback<Estados>() {
            @Override
            public void onResponse(Call<Estados> call, Response<Estados> response) {
                    estados=response.body();

                for(int i=0;i<estados.getEstados().size();i++){
                    listaEstados.add(estados.getEstados().get(i).getEstado());
                }
               adapterEstados=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listaEstados);
                spinnerEstado.setAdapter(adapterEstados);
            }

            @Override
            public void onFailure(Call<Estados> call, Throwable t) {
                Toast.makeText(getContext(), R.string.conexion, Toast.LENGTH_SHORT).show();
            }
        });



        // Inflate the layout for this fragment
        return view;
    }
    private void Inicializar(View view){
        btnBackUbicacion=(Button)view.findViewById(R.id.btnBackUbicacion);
        btnNextUbicacion=(Button)view.findViewById(R.id.btnNextUbicacion);
        services= Api.getApi().create(egresaTecNMService.class);
        estadosCall=services.getEstados("obtenerEstados");
        estados=null;
        listaEstados=new ArrayList<String>();
        adapterEstados=null;
        tecnologicosCall=null;
        tecnologicos=null;
        listaTecnologicos=new ArrayList<String>();
        adapterTecnologicos=null;
        carrerasCall=null;
        carreras=null;
        listaCarreras=new ArrayList<String>();
        adapterCarreras=null;
        spinnerEstado=(Spinner)view.findViewById(R.id.spinnerEstado);
        spinnerTecnologico=(Spinner)view.findViewById(R.id.spinnerTecnologico);
        spinnerCarrera=(Spinner)view.findViewById(R.id.spinnerCarrera);
    }
    private void enviaNumUbicacion(){
        callback.RecuperarNumPag(3);
    }
    private void enviaNumUbicacionRegresa(){
        callback.RecuperarNumPag(1);
    }

}
