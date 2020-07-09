package lesh.egresatecnm.com.egresatecnm.FragmentPerfil;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lesh.egresatecnm.com.egresatecnm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
TextView txtEmailPerfil;
    public PerfilFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_perfil, container, false);
        Inicializar(view);
        txtEmailPerfil.setText("Aqui va los datos de usuario");

        return view;
    }


    private void Inicializar(View view){
        txtEmailPerfil=(TextView)view.findViewById(R.id.txtEmailPerfil);
    }



}
