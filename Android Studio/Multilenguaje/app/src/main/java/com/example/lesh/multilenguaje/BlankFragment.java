package com.example.lesh.multilenguaje;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String hola=getString(R.string.welcome);
        Toast.makeText(getContext(),hola,Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
