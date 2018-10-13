package com.heroes.lesh.kidneys.FragmentsFollow.FragmentsFollowFollow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class FollowFollowDayHourFragment extends Fragment {
//Variables globales
    private View view;
    private Bundle bundle;
    private Unbinder unbinder;
    @BindView(R.id.textViewStartFollowFollowHour)
    TextView textViewStartFollowFollowHour;
    @BindView(R.id.textViewEndFollowFollowHour)
    TextView textViewEndFollowFollowHour;
    @BindView(R.id.textViewTypeOfSolutionFollowFollowHour)
    TextView textViewTypeOfSolutionFollowFollowHour;
    @BindView(R.id.textViewDrainageFollowFollowHour)
    TextView textViewDrainageFollowFollowHour;
    @BindView(R.id.textViewUfFollowFollowHour)
    TextView textViewUfFollowFollowHour;
    @BindView(R.id.textViewIngestedLiquidFollowFollowHour)
    TextView textViewIngestedLiquidFollowFollowHour;
    @BindString(R.string.ml)
    String ml;

    public FollowFollowDayHourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_follow_follow_day_hour, container, false);
        unbinder = ButterKnife.bind(this, view);
        bundle = getArguments();
        if(bundle!=null){
            textViewStartFollowFollowHour.append("\n"+ bundle.getString("Start"));
            textViewEndFollowFollowHour.append("\n"+bundle.getString("End"));
            textViewTypeOfSolutionFollowFollowHour.append("\n"+bundle.getDouble("TypeOfSolution"));
            textViewDrainageFollowFollowHour.append("\n"+bundle.getDouble("Drainage")+" "+ml);
            textViewUfFollowFollowHour.append("\n"+bundle.getDouble("Uf")+" "+ml);
            textViewIngestedLiquidFollowFollowHour.append("\n"+bundle.getDouble("IngestedLiquid")+" "+ml);
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
