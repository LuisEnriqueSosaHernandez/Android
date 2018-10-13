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
public class WelcomeFifthFragment extends Fragment {
private View view;

    public WelcomeFifthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_welcome_fifth, container, false);
        return view;
    }

}
