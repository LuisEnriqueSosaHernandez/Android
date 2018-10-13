package com.heroes.lesh.kidneys.FragmentsWelcome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heroes.lesh.kidneys.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFirstFragment extends Fragment {
    //variables globales
    private View view;

    public WelcomeFirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_first, container, false);
    }

}
