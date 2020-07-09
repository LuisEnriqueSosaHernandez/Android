package lesh.egresatecnm.com.fragmentos.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lesh.egresatecnm.com.fragmentos.R;

public class DetailsFragment extends Fragment {
private TextView details;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_details, container, false);

        details=(TextView) view.findViewById(R.id.txtDetails);


        // Inflate the layout for this fragment
        return view;
    }
    public void RenderText(String text){
    details.setText(text);
    }

}
