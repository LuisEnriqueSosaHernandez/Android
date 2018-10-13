package com.heroes.lesh.kidneys.FragmentsMaps;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heroes.lesh.kidneys.Adapters.Adapter_map;
import com.heroes.lesh.kidneys.Api.Api;
import com.heroes.lesh.kidneys.Interfaces.InterfacesApi.KidneysService;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.Models.Location;
import com.heroes.lesh.kidneys.Models.ModelsApi.CategoriesApi;
import com.heroes.lesh.kidneys.Models.ModelsApi.LocationsApi;

import com.heroes.lesh.kidneys.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener, LocationListener {
    //variables globales
    private View view;
    private GoogleMap gMap;
    @BindView(R.id.map)
    MapView mapView;
    @BindString(R.string.gps)
    String gps;
    @BindString(R.string.fijarlocalizacion)
    String fijarlocalizacion;
    @BindString(R.string.espera)
    String espera;
    @BindString(R.string.aceptar)
    String aceptar;
    @BindString(R.string.cancelar)
    String cancelar;
    @BindString(R.string.activaciongps)
    String activaciongps;
    @BindString(R.string.reintentardatos)
    String reintentardatos;
    @BindString(R.string.errorcarga)
    String errorcarga;
    @BindString(R.string.cargacorrecta)
    String cargacorrecta;
    @BindView(R.id.floatingGps)
    FloatingActionButton floatingGps;
    @BindDrawable(R.drawable.ic_fix_location)
    Drawable ic_fix_location;
    @BindDrawable(R.drawable.ic_off_location)
    Drawable ic_off_location;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.mipmap.ic_maps)
    Drawable ic_maps;
    private Unbinder unbinder;
    private CameraPosition camera;
    private List<Address> addresses;
    private MarkerOptions marker;
    private Marker myMarker;
    private Geocoder geocoder;
    private String locality;
    private double latitude;
    private double longitude;
    private String address;
    private Marker markerDefault;
    private int gpsSignal;
    private LocationManager locationManager;
    private android.location.Location currentLocation;
    private final int PERMISSION_LOCATION = 1;
    private int permissionAccessFineLocation;
    private int permissionAccessCoarseLocation;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private Location location;
    private LocationsApi locationsApi;
    private CategoriesApi categoriesApi;
    private ArrayList<Location> locations;
    private String category;
    private Bundle bundle;
    private String jsonLocations;
    private Gson gson;
    private ListenerPag callback;
    private KidneysService kidneysService;
    private Adapter_map adapter_map;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //Inicializacion del callback de la interfaz
            callback = (ListenerPag) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        //evento click floating button
        changeIcon();
        inicializar();
        inicializarToast();
        cargarTablasLocations();
        floatingGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIcon();
                if (!isGPSEnabled()) {
                    showInfoAlert();
                } else {
                    locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
                    //Falta pedir permiso version superior 23 para el gps
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        checkForPermission();
                    } else {
                        //Inscripcion localizacion por red y gps
                        try {
                            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        } catch (Exception e) {
                            changeToast(espera, ic_maps);
                            toast.show();
                        }
                        if (currentLocation == null) {
                            try {
                                currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            } catch (Exception e) {
                                changeToast(espera, ic_maps);
                                toast.show();
                            }
                        }
                        if (currentLocation != null) {
                            createOrUpdateMarkerByLocation(currentLocation);
                            zoomToLocation(currentLocation);
                        } else {
                            changeToast(espera, ic_maps);
                            toast.show();
                        }
                    }
                }
            }
        });
        floatingGps.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(fijarlocalizacion, ic_maps);
                toast.show();
                return false;
            }
        });
        return view;
    }
//Metodo para recargar los marcadores al pop de fragment
    public void onReturn(){
        if(markerDefault!=null){
            marker = new MarkerOptions().
                    position(markerDefault.getPosition()).
                    icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_select_location)).draggable(true);
            markerDefault = gMap.addMarker(marker);
            changeTheMarker(markerDefault.getPosition().latitude, markerDefault.getPosition().longitude, markerDefault);
        }
        if(myMarker!=null){
            marker = new MarkerOptions().
                    position(myMarker.getPosition()).
                    icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location));
            myMarker = gMap.addMarker(marker);
        }
    }
    //metodo para cargar las recetas
    private void cargarTablasLocations() {
        bundle = getArguments();
        if (bundle != null) {
            jsonLocations = bundle.getString("Locations");
            locations = gson.fromJson(jsonLocations, new TypeToken<ArrayList<Location>>() {
            }.getType());
        } else {
            changeToast(reintentardatos, ic_maps);
            toast.show();
            tryAgainCargarTablasLocations();
        }
    }

    private void tryAgainCargarTablasLocations() {
        selectCategories();
    }

    //Metodo para cargar las categorias de la api
    private void selectCategories() {
        kidneysService.selectCategoriesApi().enqueue(new retrofit2.Callback<CategoriesApi>() {
            @Override
            public void onResponse(Call<CategoriesApi> call, Response<CategoriesApi> response) {
                categoriesApi = response.body();
                selectLocations();
            }

            @Override
            public void onFailure(Call<CategoriesApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    private void selectLocations() {
        kidneysService.selectLocationsApi().enqueue(new retrofit2.Callback<LocationsApi>() {
            @Override
            public void onResponse(Call<LocationsApi> call, Response<LocationsApi> response) {
                locationsApi = response.body();
                if (locationsApi != null) {
                    for (int i = 0; i < locationsApi.getLocationApis().size(); i++) {
                        location = new Location();
                        location.setIdLocation(locationsApi.getLocationApis().get(i).getIdLocation());
                        location.setName(locationsApi.getLocationApis().get(i).getName());
                        location.setLongitude(locationsApi.getLocationApis().get(i).getLongitude());
                        location.setLatitude(locationsApi.getLocationApis().get(i).getLatitude());
                        if (categoriesApi != null) {
                            for (int j = 0; j < categoriesApi.getCategoriesApi().size(); j++) {
                                if (categoriesApi.getCategoriesApi().get(j).getIdCategory() == locationsApi.getLocationApis().get(i).getIdcategory()) {
                                    category = categoriesApi.getCategoriesApi().get(j).getName().trim();
                                    location.setCategory(category.trim());
                                    break;
                                }
                            }
                        }
                        locations.add(location);
                    }
                }
                jsonLocations = gson.toJson(locations);
                changeToast(cargacorrecta, ic_healthy);
                toast.show();
                sendJsonLocations(jsonLocations);
                createMarkersApi();
            }

            @Override
            public void onFailure(Call<LocationsApi> call, Throwable t) {
                changeToast(errorcarga, ic_harmful);
                toast.show();
                return;
            }
        });
    }

    private void sendJsonLocations(String jsonLocations) {
        callback.tryAgainJsonLocations(jsonLocations);
    }

    //Metodo llamado cuando se crea la vista solo aqui funciona el mapa
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeIcon();
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    //Preguntar por permisos api >23
    private void checkForPermission() {
        permissionAccessFineLocation = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        permissionAccessCoarseLocation = ContextCompat.checkSelfPermission(getContext(), ACCESS_COARSE_LOCATION);
        if ((permissionAccessFineLocation != PackageManager.PERMISSION_GRANTED) || permissionAccessCoarseLocation != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_LOCATION);
        }
    }

    //Resultado de la pregunta del permiso
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_LOCATION:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    } else {
                        //Inscripcion localizacion por red y gps
                        try {
                            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        } catch (Exception e) {
                            changeToast(espera, ic_maps);
                            toast.show();
                        }
                        if (currentLocation == null) {
                            try {
                                currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            } catch (Exception e) {
                                changeToast(espera, ic_maps);
                                toast.show();
                            }

                        }
                        if (currentLocation != null) {
                            createOrUpdateMarkerByLocation(currentLocation);
                            zoomToLocation(currentLocation);
                        } else {
                            changeToast(espera, ic_maps);
                            toast.show();
                        }
                    }
                }
                break;
        }
    }

    private void inicializar() {
        adapter_map = new Adapter_map(getActivity());
        locations = new ArrayList<Location>();
        category = "";
        gson = new Gson();
        kidneysService = Api.getApi().create(KidneysService.class);
    }

    //Metodo para inicializar el toast
    private void inicializarToast() {
        layoutInflater = getActivity().getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    //Metodo para cambiar el toast
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para cambiar el icono del gps
    private void changeIcon() {
        if (!isGPSEnabled()) {
            floatingGps.setImageDrawable(ic_off_location);
        } else {
            floatingGps.setImageDrawable(ic_fix_location);
        }
    }

    //Metodo para hacer cambios en el marcador
    private void changeTheMarker(double latitude, double longitude, Marker marker) {
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);//maximo 5 direcciones
        } catch (IOException e) {
            e.printStackTrace();
        }
        address = addresses.get(0).getAddressLine(0);
        locality = addresses.get(0).getLocality();
        Location location = new Location();
        location.setLocality(locality);
        location.setAddreess(address);
        //marker.setTitle(locality);
        //marker.setSnippet(address);
        //marker.setSnippet("http://i.imgur.com/DvpvklR.png");
        marker.setTag(location);
        marker.showInfoWindow();
    }

    //Metodo para hacer cambios en el marcador
    private void changeTheMarkerApi(double latitude, double longitude, Marker marker, Location location) {
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);//maximo 5 direcciones
        } catch (IOException e) {
            e.printStackTrace();
        }
        address = addresses.get(0).getAddressLine(0);
        locality = addresses.get(0).getLocality();
        location.setLocality(locality);
        location.setAddreess(address);
        switch (location.getCategory()){
            case "Dealer":
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_dealer));
                break;
            case "Hospital":
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_hospital));
                break;
            case "Clinic":
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_clinic));
                break;
        }
        //marker.setTitle(locality);
        //marker.setSnippet(address);
        //marker.setSnippet("http://i.imgur.com/DvpvklR.png");
        marker.setTag(location);
        //marker.showInfoWindow();
    }

    //Metodo para cuando el mapa esta listo
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setInfoWindowAdapter(adapter_map);
        gMap.setOnMarkerDragListener(this);
        gMap.setOnMapLongClickListener(this);
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        createMarkersApi();
        onReturn();
        //Ubicacion real
        changeIcon();
        if (!isGPSEnabled()) {
            showInfoAlert();
        } else {
            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                checkForPermission();
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 10, this);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10, this);
                try {
                    currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } catch (Exception e) {
                    changeToast(espera, ic_maps);
                    toast.show();
                }
                if (currentLocation == null) {
                    try {
                        currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    } catch (Exception e) {
                        changeToast(espera, ic_maps);
                        toast.show();
                    }
                }
                if (currentLocation != null) {
                    createOrUpdateMarkerByLocation(currentLocation);
                    zoomToLocation(currentLocation);
                } else {
                    changeToast(espera, ic_maps);
                    toast.show();
                }
            }
        }

    }

    //Mover la camara a posicion actual
    private void zoomToLocation(android.location.Location location) {
        camera = new CameraPosition.Builder().
                target(new LatLng(location.getLatitude(), location.getLongitude())).
                zoom(15).
                bearing(90).
                tilt(45).
                build();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
    }

    //crear o actualizar mi marcador de posicion actual
    private void createOrUpdateMarkerByLocation(android.location.Location location) {
        if (myMarker == null) {
            marker = new MarkerOptions().
                    position((new LatLng(location.getLatitude(), location.getLongitude()))).
                    icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location));
            myMarker = gMap.addMarker(marker);
            changeTheMarker(location.getLatitude(),
                    location.getLongitude(), myMarker);
            zoomToLocation(location);
        } else {
            myMarker.setPosition(new LatLng(
                    location.getLatitude(), location.getLongitude()));
            changeTheMarker(location.getLatitude(),
                    location.getLongitude(), myMarker);
        }
    }

    //Metodo para crear los marcadores de la api
    private void createMarkersApi() {
        for (int i = 0; i < locations.size(); i++) {
            latitude = locations.get(i).getLatitude();
            longitude = locations.get(i).getLongitude();
            marker = new MarkerOptions().
                    position((new LatLng(latitude, longitude)));
            changeTheMarkerApi(latitude, longitude, gMap.addMarker(marker), locations.get(i));
        }
    }

    //Verificar si esta habiltado el gps
    private boolean isGPSEnabled() {
        try {
            gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
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

    //Mostrar alerta de activacion de gps
    private void showInfoAlert() {
        new AlertDialog.Builder(getContext())
                .setTitle(gps).setMessage(activaciongps)
                .setPositiveButton(aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                    }
                })
                .setNegativeButton(cancelar, null)
                .show();
    }

    //Metodos para el arrastre de punteros
    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;
        changeTheMarker(latitude, longitude, marker);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //Metodos para actualizacion de gps
    @Override
    public void onLocationChanged(android.location.Location location) {
        //Toast.makeText(getContext(),"Changed->"+location.getProvider(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onMapLongClick(LatLng latLng) {
        //markerDefault.hideInfoWindow();
        latitude = latLng.latitude;
        longitude = latLng.longitude;
        if (markerDefault == null) {
            marker = new MarkerOptions().
                    position(latLng).
                    icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_select_location)).draggable(true);
            markerDefault = gMap.addMarker(marker);
            changeTheMarker(latitude, longitude, markerDefault);
        } else {
            changeTheMarker(latitude, longitude, markerDefault);
            markerDefault.setPosition(latLng);
        }
    }

    @Override
    public void onResume() {
        changeIcon();
        super.onResume();
    }
}
