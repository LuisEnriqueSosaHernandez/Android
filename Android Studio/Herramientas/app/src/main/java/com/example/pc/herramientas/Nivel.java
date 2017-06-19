package com.example.pc.herramientas;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Nivel extends Fragment implements SensorEventListener{

private SensorManager miManager;
    private Sensor miSensor;
    private NivelPantalla pantalla;
    public Nivel() {
        // Required empty public constructor
    }
public final void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    miManager=(SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
    miSensor=miManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    int lado=getResources().getDimensionPixelSize(R.dimen.maximo);
    pantalla=new NivelPantalla(getActivity(),lado);
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_nivel, container, false);
        return pantalla;
    }
    public void onResume() {
        super.onResume();
        miManager.registerListener(this, miSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause() {
        super.onPause();
        miManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    pantalla.angulos(event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
