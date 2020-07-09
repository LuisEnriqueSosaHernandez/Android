package lesh.egresatecnm.com.egresatecnm.FragmentRegistro;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lesh.egresatecnm.com.egresatecnm.Interfaces.ListenerPag;
import lesh.egresatecnm.com.egresatecnm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BienvenidoFragment extends Fragment {
    private ListenerPag callback;
    private Button btnComenzarRegistro;

    public BienvenidoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback=(ListenerPag) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString()+"algo salio mal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bienvenido, container, false);
        Inicializar(view);
        btnComenzarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaNumRegistro();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void Inicializar(View view){
        btnComenzarRegistro=(Button) view.findViewById(R.id.btnComenzarRegistro);
    }
    private void enviaNumRegistro(){
        callback.RecuperarNumPag(2);
    }

}
