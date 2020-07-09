package lesh.egresatecnm.com.egresatecnm.FragmentEmpleos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import lesh.egresatecnm.com.egresatecnm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmpleoFragment extends Fragment {
TextView txtEmpleoUnico;
String Empleo;

    public EmpleoFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_empleo, container, false);
        Inicializar(view);
        Bundle Datos=this.getArguments();
        Empleo=Datos.getString("Empleo");
        CambiarDatos(Empleo);
        // Inflate the layout for this fragment
        return view;
    }
    private void CambiarDatos(String Empleo){
        txtEmpleoUnico.setText(Empleo);
    }
    private void Inicializar(View view){
        txtEmpleoUnico=(TextView)view.findViewById(R.id.txtEmpleoUnico);
    }
}
