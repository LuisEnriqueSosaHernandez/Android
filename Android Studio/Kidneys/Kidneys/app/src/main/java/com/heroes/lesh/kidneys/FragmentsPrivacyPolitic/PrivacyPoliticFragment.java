package com.heroes.lesh.kidneys.FragmentsPrivacyPolitic;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heroes.lesh.kidneys.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacyPoliticFragment extends Fragment {
    @BindView(R.id.textViewPolitica)
    TextView textViewPolitica;
    @BindString(R.string.politica)
    String politica;
    private Unbinder unbinder;
    private View view;

    public PrivacyPoliticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_privacy_politic, container, false);
        unbinder = ButterKnife.bind(this, view);
        setTexto();
        return view;
    }

    //Metodo para cambiar el texto
    private void setTexto() {
        textViewPolitica.setText(Html.fromHtml(politica));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
