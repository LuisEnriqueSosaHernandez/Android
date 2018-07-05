package com.example.lesh.maps.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, View.OnClickListener, LocationListener {
    private View view;
    private GoogleMap gMap;
    private MapView mapView;
    private List<Address> addresses;
    private Geocoder geocoder;
    private MarkerOptions marker;
    private FloatingActionButton fab;
    private Location currentLocation;
    private LocationManager locationManager;
    private Marker marker2;
    private CameraPosition camera;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.map);
        if (mapView != null) {
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //gMap.setMyLocationEnabled(true);
        //gMap.getUiSettings().setMyLocationButtonEnabled(false);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        /*gMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(getContext(),"Hola",Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/
        /*LatLng veracruz = new LatLng(19.2059701, -96.195701);
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
        geocoder=new Geocoder(getContext(), Locale.getDefault());*/

    }
private void zoomToLocation(Location location){
    camera=new CameraPosition.Builder().
            target(new LatLng(location.getLatitude(),location.getLongitude())).
            zoom(15)
            .bearing(0)
            .tilt(30)
            .build();
    gMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
}
    private boolean isGPSEnabled() {
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
            if (gpsSignal == 0) {
                return false;

            } else {
                return true;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showInfoAlert() {
        new AlertDialog.Builder(getContext())
                .setTitle("GPS Signal").setMessage("You dont have GPS Singnal activate")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("    Cancel", null)
                .show();
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
        double latitude = marker.getPosition().latitude;
        double longitude = marker.getPosition().longitude;

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);//maximo 5 direcciones
        } catch (IOException e) {
            e.printStackTrace();
        }
        String address = addresses.get(0).getAddressLine(0);
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        marker.setSnippet("address: " + address);
        marker.showInfoWindow();
        /*Toast.makeText(getContext(),"adress: "+adress+"\n"+
        "city: "+city+"\n"+
                "state: "+state+"\n"+
                "country: "+country+"\n"+
                "postalCode: "+postalCode+"\n",
                Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onClick(View v) {
        if (!isGPSEnabled()) {
            showInfoAlert();
        } else {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            currentLocation = location;
            if (currentLocation != null) {
createOrUpdateMarkerByLocation(currentLocation);
zoomToLocation(currentLocation);
            }
        }
    }
    private void createOrUpdateMarkerByLocation(Location location){
        if(marker2==null){
            marker2=gMap.addMarker( new MarkerOptions().position((new LatLng(location.getLatitude(),location.getLongitude()))).draggable(true));
        }else{
            marker2.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getContext(),"Changed->"+location.getProvider(),Toast.LENGTH_SHORT).show();
        createOrUpdateMarkerByLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
