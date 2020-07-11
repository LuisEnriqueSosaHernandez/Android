package itver.donapps.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import itver.donapps.R;
import itver.donapps.activitys.ListDonorsFoundActivity;
import itver.donapps.models.Cities;
import itver.donapps.models.City;
import itver.donapps.utils.Utils;

public class SearchFragment extends Fragment {

    private Spinner spinnerCity;
    private Spinner spinnerBloodGroup;
    private Button btnSearchDonors;


    public SearchFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        spinnerCity = (Spinner) v.findViewById(R.id.spinnerCitySearch);
        spinnerBloodGroup = (Spinner) v.findViewById(R.id.spinnerBloodGroupSearch);
        btnSearchDonors = (Button) v.findViewById(R.id.btnSearchDonors);

        //SPINNER CITIES
        ArrayAdapter<City> adapterCities = new ArrayAdapter<City>
                (getContext(), android.R.layout.simple_spinner_dropdown_item
                        , Cities.getCities());
        spinnerCity.setAdapter(adapterCities);
        int positionCity = 0;
        try {
            for (City city: Cities.getCities() ) {
                if(Utils.getCurrentUser().getCity() == city.getId()){
                    break;
                }
                positionCity++;
            }
        }catch (Exception e){
Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT);
        }

        spinnerCity.setSelection(positionCity);


        //SPINNER BLOOD GROUP
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>
                                        (getContext(), android.R.layout.simple_spinner_dropdown_item
                                        , Utils.getBloodGroups());
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
        int positionGB = 0;
        try {
            for(String gb: Utils.getBloodGroups()){
                if(Utils.getCurrentUser().getBloodGroup().equals(gb)){
                    break;
                }
                positionGB++;
            }
        }catch (Exception e){
            Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT);
        }
       spinnerBloodGroup.setSelection(positionGB);

        btnSearchDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = spinnerCity.getSelectedItem().toString();
                String bloodGroup = spinnerBloodGroup.getSelectedItem().toString();
                Bundle b = new Bundle();
                b.putInt("city", Cities.getCityID(city));
                b.putString("bloodGroup", bloodGroup);
                Intent i = new Intent(getActivity(), ListDonorsFoundActivity.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        return v;
    }
}
