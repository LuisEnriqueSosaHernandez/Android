package com.example.pc.herramientas;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Linterna extends Fragment {
private ImageView botonCamara;
private boolean encendida=false;

    public Linterna() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmento= inflater.inflate(R.layout.fragment_linterna, container, false);
        botonCamara=(ImageView)fragmento.findViewById(R.id.linterna);
        botonCamara.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v) {
            if(encendida){
                botonApagaFlash();
                encendida=false;
            }else{
                botonEnciendeFlash();
                encendida=true;
            }
        }
    });
        return fragmento;
    }
    public void botonEnciendeFlash(){
        botonCamara.setImageResource(R.drawable.linterna2);
        Activity estaActividad=getActivity();
        ((ManejaFlashCamara)estaActividad).enciendeApaga(encendida);
    }
    public void botonApagaFlash(){
        botonCamara.setImageResource(R.drawable.linterna);
        Activity estaActividad=getActivity();
        ((ManejaFlashCamara)estaActividad).enciendeApaga(encendida);
    }

}
