package lesh.egresatecnm.com.egresatecnm.FragmentEncuesta;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import lesh.egresatecnm.com.egresatecnm.Interfaces.ListenerPag;
import lesh.egresatecnm.com.egresatecnm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Seccion1Fragment extends Fragment {
    private ListenerPag callback;
    private TextView txtNumPag1;
    private Button next1;
    private Button back1;
    public Seccion1Fragment() {
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
        View view=inflater.inflate(R.layout.fragment_seccion1, container, false);
        Inicializar(view);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    enviaNumEncuesta();
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaNumEncuestaRegresa();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void enviaNumEncuesta(){
        callback.RecuperarNumPag(Integer.parseInt(txtNumPag1.getText().toString())+1);
    }
    private void enviaNumEncuestaRegresa(){
        callback.RecuperarNumPag(Integer.parseInt(txtNumPag1.getText().toString())-1);
    }
    private void Inicializar(View view){
        next1=(Button) view.findViewById(R.id.next1);
        back1=(Button) view.findViewById(R.id.back1);
        txtNumPag1=(TextView)view.findViewById(R.id.txtNumPag1);
    }
}
