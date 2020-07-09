package lesh.egresatecnm.com.egresatecnm.FragmentPolitica;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lesh.egresatecnm.com.egresatecnm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoliticaFragment extends Fragment {
TextView txtPolitica;

    public PoliticaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_politica, container, false);
        Inicializar(view);
        txtPolitica.setText( Html.fromHtml( getString(R.string.politica) ) );
        // Inflate the layout for this fragment
        return view;
    }
    private void Inicializar(View view){
        txtPolitica=(TextView)view.findViewById(R.id.txtPolitica);
    }

}
