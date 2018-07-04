package com.example.lesh.maps.fragments;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lesh.maps.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback,GoogleMap.OnMarkerDragListener {
private View view;
private GoogleMap gMap;
private MapView mapView;
private List<Address> addresses;
private Geocoder geocoder;
private MarkerOptions marker;
    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView=view.findViewById(R.id.map);
        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            /*mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                }
            });*/
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;
        LatLng veracruz = new LatLng(19.2059701, -96.195701);
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(15);

        marker=new MarkerOptions();
        marker.position(veracruz);
        marker.title("Mi marcador");
        marker.draggable(true);
        marker.snippet("Esto es una caja de texto");
       // marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.common_google_signin_btn_icon_dark));
        gMap.addMarker(marker);
        //gMap.addMarker(new MarkerOptions().position(veracruz).title("Hola desde Veracruz").draggable(true));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(veracruz));
        gMap.animateCamera(zoom);

        gMap.setOnMarkerDragListener(this);
        geocoder=new Geocoder(getContext(), Locale.getDefault());
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitude=marker.getPosition().latitude;
        double longitude=marker.getPosition().longitude;

        try {
            addresses=geocoder.getFromLocation(latitude,longitude,1);//maximo 5 direcciones
        } catch (IOException e) {
            e.printStackTrace();
        }
        String address=addresses.get(0).getAddressLine(0);
        String city=addresses.get(0).getLocality();
        String state=addresses.get(0).getAdminArea();
        String country=addresses.get(0).getCountryName();
        String postalCode=addresses.get(0).getPostalCode();
        marker.setSnippet("address: "+address);
        marker.showInfoWindow();
        /*Toast.makeText(getContext(),"adress: "+adress+"\n"+
        "city: "+city+"\n"+
                "state: "+state+"\n"+
                "country: "+country+"\n"+
                "postalCode: "+postalCode+"\n",
                Toast.LENGTH_SHORT).show();*/
    }
}
