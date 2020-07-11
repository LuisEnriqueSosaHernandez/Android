package itver.donapps.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itver.donapps.R;

public class InfoFragment extends Fragment {

    public InfoFragment(){

    }

    private TextView txtMision;
    private TextView txtVision;
    private TextView txtGoals;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_information, container, false);
        txtMision = (TextView) v.findViewById(R.id.txtMision);
        txtVision = (TextView) v.findViewById(R.id.txtVision);
        txtGoals = (TextView) v.findViewById(R.id.txtGoals);

        txtMision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInformationFragment f = new ShowInformationFragment();
                Bundle b = new Bundle();
                b.putString("title", getString(R.string.mision));
                b.putString("content", getString(R.string.mision_detalle));
                f.setArguments(b);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, f)
                        .addToBackStack(null).commit();

            }
        });

        txtVision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInformationFragment f = new ShowInformationFragment();
                Bundle b = new Bundle();
                b.putString("title", getString(R.string.vision));
                b.putString("content", getString(R.string.vision_detalle));
                f.setArguments(b);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, f)
                        .addToBackStack(null).commit();
            }
        });

        txtGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInformationFragment f = new ShowInformationFragment();
                Bundle b = new Bundle();
                b.putString("title", getString(R.string.objetivo));
                b.putString("content", getString(R.string.objetivo_detalle));
                f.setArguments(b);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, f)
                        .addToBackStack(null).commit();
            }
        });


        return v;
    }
}
