package com.example.pc.herramientas;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment {
private final  int[] BOTONESMENU={R.id.linterna,R.id.musica,R.id.nivel};

    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mimenu= inflater.inflate(R.layout.fragment_menu, container, false);
        ImageButton botonmenu;
        for (int i=0;i<BOTONESMENU.length;i++){
            botonmenu=(ImageButton) mimenu.findViewById(BOTONESMENU[i]);
            final int queboton=i;
            botonmenu.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Activity estaActividad=getActivity();
                    ((ComunicaMenu)estaActividad).menu(queboton);
                }
            });
        }
        return mimenu;
    }


}
