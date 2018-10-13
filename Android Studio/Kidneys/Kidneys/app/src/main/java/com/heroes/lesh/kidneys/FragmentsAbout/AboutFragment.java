package com.heroes.lesh.kidneys.FragmentsAbout;


import android.os.Bundle;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    @BindView(R.id.textViewAbout)
    TextView textViewAbout;
    @BindString(R.string.about)
    String about;
    private Unbinder unbinder;
    private View view;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);
        unbinder = ButterKnife.bind(this, view);
        setTexto();
        return view;
    }

    //Metodo para cambiar texto
    private void setTexto() {
        textViewAbout.setText(Html.fromHtml(about));
    }

    //Metodo para destruir el bin de los elementos
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
