package itver.donapps.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import itver.donapps.R;
import itver.donapps.activitys.ProfileUpdateActivity;
import itver.donapps.models.Cities;
import itver.donapps.models.User;
import itver.donapps.utils.Utils;

public class ProfileFragment extends Fragment {


    public ProfileFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        //Display Information User stored in Utils->current_user
        User u = Utils.getCurrentUser();
        TextView txtName = (TextView) v.findViewById(R.id.txtNameProfile);
        TextView txtStatus = (TextView) v.findViewById(R.id.txtStatusProfile);
        TextView txtBloodGroup = (TextView) v.findViewById(R.id.txtBloodGroupProfile);
        TextView txtPhone = (TextView) v.findViewById(R.id.txtPhoneProfile);
        TextView txtEmail = (TextView) v.findViewById(R.id.txtEmailProfile);
        TextView txtCity = (TextView) v.findViewById(R.id.txtCityProfile);

        FloatingActionButton btnEditProfile = (FloatingActionButton) v.findViewById(R.id.btnEditProfile);

        if(u != null){
            txtName.setText(u.getName() + " " + u.getLastName());
            txtStatus.setText(u.getStatus());
            txtBloodGroup.setText(u.getBloodGroup());
            txtPhone.setText(u.getPhone());
            txtEmail.setText(u.getEmail());
            txtCity.setText(Cities.getCities().get(u.getCity()-1).getName());
        }

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(
                        new Intent(getActivity(), ProfileUpdateActivity.class));
            }
        });

        return v;
    }
}
