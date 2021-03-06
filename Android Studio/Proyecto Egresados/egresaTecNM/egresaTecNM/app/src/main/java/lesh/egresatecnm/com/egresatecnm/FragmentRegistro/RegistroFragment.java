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
public class RegistroFragment extends Fragment {
    private ListenerPag callback;
    private Button btnBackRegistro;
    private Button btnFinishRegistro;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback=(ListenerPag) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString()+"algo salio mal");
        }
    }
    public RegistroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registro, container, false);
        Inicializar(view);
        btnBackRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             enviaNumRegistroRegresa();
            }
        });
        btnFinishRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaNumRegistro();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
private void Inicializar(View view){
    btnBackRegistro=(Button)view.findViewById(R.id.btnBackRegistro);
    btnFinishRegistro=(Button)view.findViewById(R.id.btnFinishRegistro);
}
    private void enviaNumRegistro(){
        callback.RecuperarNumPag(4);
    }
    private void enviaNumRegistroRegresa(){
        callback.RecuperarNumPag(2);
    }
}







