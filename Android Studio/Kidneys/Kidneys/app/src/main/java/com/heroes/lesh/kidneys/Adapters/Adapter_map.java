package com.heroes.lesh.kidneys.Adapters;

import android.app.Activity;
import android.content.Context;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.heroes.lesh.kidneys.Models.Location;
import com.heroes.lesh.kidneys.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_map implements GoogleMap.InfoWindowAdapter {
    //Variables globales
    private View view;
    @BindView(R.id.textViewLocalityCustomMap)
    TextView textViewLocalityCustomMap;
    @BindView(R.id.textViewAddressCustomMap)
    TextView textViewAddressCustomMap;
    @BindDrawable(R.drawable.error)
    Drawable errorImage;
    @BindView(R.id.textViewNameCustomMap)
    TextView textViewNameCustomMap;
    private Location location;
    private Context context;

    public Adapter_map(Context ctx) {
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.custom_map_info_window, null);
        ButterKnife.bind(this, view);


        location = (Location) marker.getTag();

        textViewNameCustomMap.setText(location.getName());
        textViewLocalityCustomMap.setText(location.getLocality());
        textViewAddressCustomMap.setText(location.getAddreess());
        return view;
    }
}
