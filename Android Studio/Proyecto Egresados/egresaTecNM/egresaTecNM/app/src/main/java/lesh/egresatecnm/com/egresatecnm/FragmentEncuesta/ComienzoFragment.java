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
public class ComienzoFragment extends Fragment {
Button next;
    TextView txtNumPag;
private ListenerPag callback;
    public ComienzoFragment() {
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
        View view=inflater.inflate(R.layout.fragment_comienzo, container, false);
        Inicializar(view);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaNumEncuesta();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void enviaNumEncuesta(){
        callback.RecuperarNumPag(Integer.parseInt(txtNumPag.getText().toString())+1);
    }
    private void Inicializar(View view){
        next=(Button) view.findViewById(R.id.next);
        txtNumPag=(TextView)view.findViewById(R.id.txtNumPag);
    }
}
