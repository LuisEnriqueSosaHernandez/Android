package com.example.pc.herramientas;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Musica extends Fragment {

    private boolean encendida;
    private ImageView botonMusica;
    public Musica() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        encendida=false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmento= inflater.inflate(R.layout.fragment_musica, container, false);
        botonMusica=(ImageView)fragmento.findViewById(R.id.musica);
        botonMusica.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View vista) {
                if (encendida){
                    apagaMusica();
                }else{
                    enciendeMusica();
                }
            }
        });
        return fragmento;
    }
    public void enciendeMusica(){
        botonMusica.setImageResource(R.drawable.musica2);
        Intent miReproductor=new Intent(getActivity(),ServicioMusica.class);
        getActivity().startService(miReproductor);
        encendida=!encendida;
    }
    public void apagaMusica(){
        botonMusica.setImageResource(R.drawable.musica);
        Intent miReproductor=new Intent(getActivity(),ServicioMusica.class);
        getActivity().stopService(miReproductor);
        encendida=!encendida;
    }
}
